import org.stringtemplate.v4.*;
import java.io.File;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class EncryptCompilerVisitor extends EncryptBaseVisitor<ST> {
   protected String target = "java"; 
   private STGroup templates = new STGroupFile(target+".stg");
   private Map<String, String> varMap = new HashMap<String, String>();
   private ConfigVisitor confVisit = new ConfigVisitor();
   public boolean validTarget(String target) {
      File f = new File(target+".stg");

      return ("java".equalsIgnoreCase(target)) &&
             f.exists() && f.isFile() && f.canRead();
   }

   public void setTarget(String target) {
      assert validTarget(target);
      this.target = target;
   }


    @Override public ST visitMain(EncryptParser.MainContext ctx) {
      assert validTarget(target);
      //This will create the main using the template module in the targeted language that is java.
      ST res = templates.getInstanceOf("module");
      Iterator<EncryptParser.StatContext> list=ctx.statList().stat().iterator();
      while(list.hasNext()){
         EncryptParser.StatContext statCtx = list.next();
         //It will check if each stat is a function stat or not and if it is a function stat it will add it to the end of the template
         if(statCtx.function() != null){
            res.add("function",visit(statCtx).render());
         }else{
            res.add("stat", visit(statCtx).render());
         }
      }
      return res;
   }
   
   @Override public ST visitStat(EncryptParser.StatContext ctx){
      if(ctx.stat2() != null){
         ST stat = templates.getInstanceOf("stats");
         CharStream input = CharStreams.fromString(ctx.getText());
         EncryptConfigLexer lexer = new EncryptConfigLexer(input);
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         EncryptConfigParser parser = new EncryptConfigParser(tokens);
         ParseTree tree = parser.stat2();
         stat.add("stat", confVisit.visit(tree).render());
         return stat;
      }
      return visitChildren(ctx);
   }

   @Override public ST visitDeclaration(EncryptParser.DeclarationContext ctx) {
      //Here we create declarations of types and IDS.
      ST res = templates.getInstanceOf("decl");
      res.add("type",visit(ctx.type()).render());
      // It is an assignemnt so we get the assignment that will have the ID and the valued assigned to the id
      if(ctx.ID()==null){
         res.add("value",visit(ctx.assignment()).render());
         
      }
      // It is an ID so we get the ID
      else{
         String id = ctx.ID().getText();
         res.add("var",id);
      }
      return res;
   }

   @Override public ST visitAssignment(EncryptParser.AssignmentContext ctx) {
      //Here we will assign a value to an id;
      ST assg= templates.getInstanceOf("assg");
      String id = ctx.ID().getText();
      assg.add("var", id);
      assg.add("value", visit(ctx.expr()).render());
      return assg;
   }

   @Override public ST visitList(EncryptParser.ListContext ctx) {
      //Here we will create a list
      ST lis=templates.getInstanceOf("list");
      lis.add("type",visit(ctx.type()).render());
      //assigned to this ID
      String id = ctx.ID().getText();
      lis.add("var",id); 
      //that we will add the assigned arguments to the list
      ST add = templates.getInstanceOf("addArg");
      Iterator<EncryptParser.ExprContext> list=ctx.expr().iterator();
      //through the following loop using the iterator "list"
      while(list.hasNext()){
         add = templates.getInstanceOf("addArg");
         add.add("name",id);
         add.add("arg",visit(list.next()).render());
         lis.add("add", add.render());
      }
      //we save the id and its type for the forLoop visitor
      varMap.put(id, visit(ctx.type()).render());
      return lis;
   }


   @Override public ST visitEncryptAlg(EncryptParser.EncryptAlgContext ctx){
      ST stat = templates.getInstanceOf("stats");
      CharStream input = CharStreams.fromString(ctx.getText());
      EncryptConfigLexer lexer = new EncryptConfigLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      EncryptConfigParser parser = new EncryptConfigParser(tokens);
      ParseTree tree = parser.cripto();
      stat.add("stat", confVisit.visit(tree).render());
      return stat;
   }


   @Override public ST visitDecryptAlg(EncryptParser.DecryptAlgContext ctx){
      ST stat = templates.getInstanceOf("stats");
      CharStream input = CharStreams.fromString(ctx.getText());
      EncryptConfigLexer lexer = new EncryptConfigLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      EncryptConfigParser parser = new EncryptConfigParser(tokens);
      ParseTree tree = parser.cripto();
      stat.add("stat", confVisit.visit(tree).render());
      return stat;
   }
   @Override public ST visitDict(EncryptParser.DictContext ctx) {
      //We will create dictionaries
      ST dict=templates.getInstanceOf("dict");
      dict.add("type1",visit(ctx.type(0)).render());
      dict.add("type2",visit(ctx.type(1)).render());
      //assigned to an ID
      String id = ctx.ID().getText();
      dict.add("var",id); 
      //that we will add the assigned arguments to the list
      ST add = templates.getInstanceOf("addArg");
      Iterator<EncryptParser.ExprContext> list=ctx.expr().iterator();
      //through the following loop using the iterator "list"
      while(list.hasNext()){
         add = templates.getInstanceOf("addArg");
         add.add("name",id);
         add.add("arg",visit(list.next()).render());
         add.add("arg2",visit(list.next()).render());
         dict.add("add", add.render());
      }
      return dict;
   }

   @Override public ST visitFile(EncryptParser.FileContext ctx) {
      ST res = templates.getInstanceOf("fileDecl");
      //This will create a file type assigned to an ID
      String idx = ctx.ID(0).getText();
      res.add("var",idx);
      //if it is a string then
      if(ctx.ID(1)==null){
         String id = ctx.STRING().getText();
         res.add("path",id);
      }
      //if it is an ID
      else{
         String idxx = ctx.ID(1).getText();
         res.add("path",idxx);
      }
      return res;
   }

   @Override public ST visitReadOperation(EncryptParser.ReadOperationContext ctx) {
      //This will read everything in the file and will assign the data to the var
      ST read = templates.getInstanceOf("readOpr");
      read.add("var",ctx.ID(0).getText());
      read.add("fileName",ctx.ID(1).getText());
      return read;
   }

   @Override public ST visitWriteOperation(EncryptParser.WriteOperationContext ctx) {
      //This will write everything in the var into the file
      ST write = templates.getInstanceOf("writeOpr");
      write.add("var",visit(ctx.expr()).render());
      write.add("fileName",ctx.ID().getText());
      return write;
   }

   @Override public ST visitReadbOperation(EncryptParser.ReadbOperationContext ctx) {
      //This will read everything in the file and save it into an array of bytes
      ST read = templates.getInstanceOf("readbOpr");
      read.add("var",ctx.ID(0).getText());
      read.add("fileName",ctx.ID(1).getText());
      return read;
   }

   @Override public ST visitWritebOperation(EncryptParser.WritebOperationContext ctx) {
      //This will write using an array of bytes and write it in the file as a string
      ST write = templates.getInstanceOf("writebOpr");
      write.add("var",visit(ctx.expr()).render());
      write.add("fileName",ctx.ID().getText());
      return write;
   }
   @Override public ST visitFunctionOperation(EncryptParser.FunctionOperationContext ctx) {
      //This is just the call to a function
      ST operation = templates.getInstanceOf("stats");
      operation.add("stat", ctx.getText() + ";");
      return operation;
   }
   @Override public ST visitIdcallOperation(EncryptParser.IdcallOperationContext ctx) {
      //This is the call for an ID operation
      ST operation = templates.getInstanceOf("stats");
      operation.add("stat", ctx.getText() + ";");
      return operation;
   }
   @Override public ST visitConditional(EncryptParser.ConditionalContext ctx) {
      //Here we will check the conditional (if,else if,else)
      ST conditionIf = templates.getInstanceOf("if");
      //Every "if condition" starts with "if" and a "condition"
      conditionIf.add("condition", visit(ctx.expr(0)).render());
      Iterator<EncryptParser.StatContext> list=ctx.trueSL.stat().iterator();
      //then we add each stat when this if is true
      while(list.hasNext()){
         conditionIf.add("stat_in", visit(list.next()).render());
      }
      //Here we check if we got any "else if" expressions
      if(ctx.expr().size() > 1){
         ST conditionTemp; 
         //we get the expr list from the possible pool, only the "if" and "else if" conditions will have expr
         Iterator<EncryptParser.ExprContext> exprList = ctx.expr().iterator();
         //we skip the 1st expr that is used in the "if" condition
         exprList.next();
         int indx = 1;
         //and we start iterating each expr that will be each "else if" condition
         while(exprList.hasNext()){
            conditionTemp = templates.getInstanceOf("elif"); 
            conditionTemp.add("stat_before", conditionIf);
            conditionTemp.add("condition", visit(exprList.next()).render());
            list = ctx.statList(indx).stat().iterator();
            //in each "else if" we add its associated stats
            while(list.hasNext()){
               conditionTemp.add("stat_in", visit(list.next()).render());
            }
            indx++;
            conditionIf = conditionTemp;
         }
      }
      //this checks if there is an "else" condition
      if(ctx.falseSL != null){
         ST conditionElse = templates.getInstanceOf("else");
         list = ctx.falseSL.stat().iterator();
         while(list.hasNext()){
            conditionElse.add("stat_in", visit(list.next()).render());
         }
         conditionElse.add("stat_before", conditionIf.render());
         return conditionElse;
      }
      return conditionIf;
   }

   @Override public ST visitWhileLoop(EncryptParser.WhileLoopContext ctx) {
      //Here we will create the while
      ST whileDo = templates.getInstanceOf("whileDo");
      whileDo.add("condition", visit(ctx.expr()).render());
      Iterator<EncryptParser.StatContext> list=ctx.trueSL.stat().iterator();
      while(list.hasNext()){
         whileDo.add("stat_in", visit(list.next()).render());
      }
      return whileDo;
   }

   @Override public ST visitForLoop(EncryptParser.ForLoopContext ctx) {
      //This is going to create a for range loop or a for each loop
      //If it is a for range loop
      if(ctx.INTEGER(0) != null){
         //it does this
         ST forLoop = templates.getInstanceOf("rangeforloop");
         String startLoop = ctx.INTEGER(0).getText();
         String endLoop = ctx.INTEGER(1).getText();
         if(ctx.expr() == null){
            forLoop.add("jump", 1);
            forLoop.add("comparison", "<");
            forLoop.add("value", Integer.parseInt(startLoop));
            forLoop.add("max", Integer.parseInt(endLoop));
         }
         else{
            forLoop.add("jump", visit(ctx.expr()).render());
            String jumpRender = visit(ctx.expr()).render().replace("(","").replace(")", "");
            if(Integer.parseInt(jumpRender) > 0){
               forLoop.add("comparison", "<");
               forLoop.add("value", Integer.parseInt(startLoop));
               forLoop.add("max", Integer.parseInt(endLoop));
            }
            else{
               forLoop.add("comparison", ">");
               forLoop.add("value", Integer.parseInt(endLoop));
               forLoop.add("max", Integer.parseInt(startLoop));
            }
         }
         if(ctx.declaration() != null)
            forLoop.add("var", ctx.declaration().ID().getText());
         else
            forLoop.add("var", ctx.ID(0).getText());
         Iterator<EncryptParser.StatContext> list = ctx.statList().stat().iterator();
         while(list.hasNext())
           forLoop.add("stat_in", visit(list.next()).render());
        return forLoop;
      }
      //If it is a for each loop
      //it does this
      ST forLoop = templates.getInstanceOf("rangeloop");
      int idLength = ctx.ID().size();
      forLoop.add("value", ctx.ID(idLength-1).getText());
      if(ctx.declaration() != null){
         forLoop.add("var", ctx.declaration().ID().getText());
         forLoop.add("type", varMap.get(ctx.ID(0).getText()));
      }
      else{
         forLoop.add("var", ctx.ID(0).getText());
         forLoop.add("type", varMap.get(ctx.ID(1).getText()));
      }
      Iterator<EncryptParser.StatContext> list = ctx.statList().stat().iterator();
      while(list.hasNext())
        forLoop.add("stat_in", visit(list.next()).render());
      return forLoop;
   }

   @Override public ST visitDoWhileLoop(EncryptParser.DoWhileLoopContext ctx) {
      //this creates a do while
      ST doWhile = templates.getInstanceOf("doWhile");
      doWhile.add("condition", visit(ctx.expr()).render());
      Iterator<EncryptParser.StatContext> list=ctx.trueSL.stat().iterator();
      while(list.hasNext()){
         doWhile.add("stat_in", visit(list.next()).render());
      }
      return doWhile;
   }

   @Override public ST visitSwitchSelect(EncryptParser.SwitchSelectContext ctx) {
      //this creates a switch select
      ST switchS = templates.getInstanceOf("switchS");
      switchS.add("var", ctx.ID().getText());
      //Here we get a list of all cases available in the context
      Iterator<EncryptParser.ExprContext> listOfCases = ctx.expr().iterator();
      Iterator<EncryptParser.StatContext> list;
      int indx= 0;
      //if the iterator is empty it will skip this while
      while(listOfCases.hasNext()){
         ST caseS = templates.getInstanceOf("caseS");
         caseS.add("var", visit(listOfCases.next()).render());
         list = ctx.statList(indx).stat().iterator();
         while(list.hasNext()){
            caseS.add("stat_in", visit(list.next()).render());
         }
         indx++;
         switchS.add("case", caseS.render());
      }
      //Here we check if default exists or not
      if(ctx.defalt != null){
         ST defaultS = templates.getInstanceOf("defaultS");
         list=ctx.defalt.stat().iterator();
         while(list.hasNext()){
            defaultS.add("stat_in", visit(list.next()).render());
         }
         switchS.add("defaulter", defaultS.render());
      }
      return switchS;
   }

    @Override public ST visitFunction(EncryptParser.FunctionContext ctx) {
      //this is used to create a function that will be added later in the visit main
      ST func= templates.getInstanceOf("function");
      func.add("name", ctx.ID(0).getText());
      Iterator<EncryptParser.TypeContext> listOfTypes = ctx.type().iterator();
      int indx= 0;
      boolean multipleArgsFlag = false;
      //here we go through each Type available in the input arguments of the function
      //function ID uses ->type ID , ->type ID   ......
      while(listOfTypes.hasNext()){
         //here we check if the type is a return type or the last type in the function ctx
         if(indx+1 == ctx.type().size() && ctx.ID().size() == ctx.type().size()){
            func.add("returnType",listOfTypes.next().getText());
         //if not it is a input type
         }else{
            ST input= templates.getInstanceOf("input");
            //there is more than one input type
            if(multipleArgsFlag){
               input.add("sep",", ");
               input.add("argType",listOfTypes.next().getText());
               input.add("arg", ctx.ID(indx+1).getText());
               func.add("arg",input.render());
               //this is the last input type
            }else{
               multipleArgsFlag = true;
               input.add("argType",listOfTypes.next().getText());
               input.add("arg", ctx.ID(indx+1).getText());
               func.add("arg",input.render());
            }
         }
         indx++;
      }
      Iterator<EncryptParser.StatContext> list;
      list=ctx.statList().stat().iterator();
      //finally we go through each stat that the function will execute
      while(list.hasNext()){
         func.add("stat_in", visit(list.next()).render());
      }
      if(ctx.expr() != null){
         func.add("stat_in", "return " + visit(ctx.expr()).render() + ";");
      }
      return func;
   }
   
   @Override public ST visitShowNormal(EncryptParser.ShowNormalContext ctx) {
      //This will create a print
      ST print= templates.getInstanceOf("print");
      print.add("value", visit(ctx.expr()).render());
      return print;
   }

   @Override public ST visitType(EncryptParser.TypeContext ctx) {
      //we get the type
      ST varType=templates.getInstanceOf("stats");
      varType.add("stat",ctx.getText());
      return varType;
   }

   @Override public ST visitStrExpr(EncryptParser.StrExprContext ctx) {
      //we get the string
         ST assg= templates.getInstanceOf("expr");
         assg.add("stat", ctx.getText());
         return assg; 
   }

   @Override public ST visitAddSubExpr(EncryptParser.AddSubExprContext ctx) {
      //we create a add or a subtract expression
      ST assg= templates.getInstanceOf("expr");
      assg.add("stat", (visit(ctx.expr(0)).render() + ctx.op.getText() + visit(ctx.expr(1)).render()));
      return assg; 
   }

   @Override public ST visitIntegerExpr(EncryptParser.IntegerExprContext ctx) {
      //we get a integer
         ST assg= templates.getInstanceOf("expr");
         assg.add("stat", ctx.getText());
         return assg;   
   }

   @Override public ST visitSignExpr(EncryptParser.SignExprContext ctx) {
      // we get associate a sign "+" or "-" to a number
      ST assg= templates.getInstanceOf("expr");
         assg.add("stat", ctx.sign.getText() + visit(ctx.expr()).render());
      return assg; 
   }

   @Override public ST visitRealExpr(EncryptParser.RealExprContext ctx) {
      //we get a "real" value
         ST assg= templates.getInstanceOf("expr");
         assg.add("stat", ctx.getText());
         return assg; 
   }

   @Override public ST visitBooleanExpr(EncryptParser.BooleanExprContext ctx) {
         //we get a boolean
         ST assg= templates.getInstanceOf("expr");
         assg.add("stat", ctx.getText());
         return assg; 
   }

   @Override public ST visitMultDivExpr(EncryptParser.MultDivExprContext ctx) {
      //we get a multiplication or a division
      ST assg= templates.getInstanceOf("expr");
      assg.add("stat", (visit(ctx.expr(0)).render() + ctx.op.getText() + visit(ctx.expr(1)).render()));
      return assg; 
   }


   @Override public ST visitComparisonExpr(EncryptParser.ComparisonExprContext ctx) {
      //we will get every possible comparison combination
      ST assg= templates.getInstanceOf("expr");
      String opos = "";
      int cut = 0;
      //we check if there is a ! meaning it is the opposite
      if(ctx.op.getText().substring(0, 1).equals("!")){
         opos = "!";
         cut = 1;
      }
      //then we go through each case checking what comparison it is
      switch(ctx.op.getText().substring(cut,ctx.op.getText().length())){
         case "=":
         assg.add("stat", opos + "(" + (visit(ctx.expr(0)).render() + "==" + visit(ctx.expr(1)).render()) + ")" );
         break;
         case ">":
         assg.add("stat", opos + "(" + (visit(ctx.expr(0)).render() + ">" + visit(ctx.expr(1)).render()) + ")" );
         break;
         case "<":
         assg.add("stat", opos + "(" + (visit(ctx.expr(0)).render() + "<" + visit(ctx.expr(1)).render()) + ")" );
         break;
         case ">=":
         assg.add("stat", opos + "(" + (visit(ctx.expr(0)).render() + ">=" + visit(ctx.expr(1)).render()) + ")" );
         break;
         case "=>":
         assg.add("stat", opos + "(" + (visit(ctx.expr(0)).render() + ">=" + visit(ctx.expr(1)).render()) + ")" );
         break;
         case "<=":
         assg.add("stat", opos + "(" + (visit(ctx.expr(0)).render() + "<=" + visit(ctx.expr(1)).render()) + ")" );
         break;
         case "=<":
         assg.add("stat", opos + "(" + (visit(ctx.expr(0)).render() + "<=" + visit(ctx.expr(1)).render()) + ")" );
         break;
      }
      return assg; 
   }
   @Override public ST visitAddcomparisonExpr(EncryptParser.AddcomparisonExprContext ctx) {
      //here we check if it is an "and" or an "or" comparison condition 
      ST assg= templates.getInstanceOf("expr");
      switch(ctx.op.getText()){
         case "and":
         assg.add("stat", "(" + (visit(ctx.expr(0)).render() + " && " + visit(ctx.expr(1)).render()) + ")" );
         break;
         case "or":
         assg.add("stat", "(" + (visit(ctx.expr(0)).render() + " || " + visit(ctx.expr(1)).render()) + ")" );
         break;
      }
      return assg;
   }
   @Override public ST visitPowExpr(EncryptParser.PowExprContext ctx) {
      //here we get the power of a number
      ST assg= templates.getInstanceOf("expr");
      assg.add("stat", "Math.pow(" + (visit(ctx.expr(0)).render() + "," + visit(ctx.expr(1)).render() + ")"));
      return assg;
   }

   @Override public ST visitParenExpr(EncryptParser.ParenExprContext ctx) {
      //check if it is a parent expression or not
      ST assg= templates.getInstanceOf("expr");
      assg.add("stat", visit(ctx.expr()).render());
      return assg; 
   }

   @Override public ST visitIdExpr(EncryptParser.IdExprContext ctx) {
      //get ID
      ST assg= templates.getInstanceOf("expr");
      assg.add("stat", ctx.getText());
      return assg; 
   }
   @Override public ST visitFuncallExpr(EncryptParser.FuncallExprContext ctx) {
      //call a function
      ST expr= templates.getInstanceOf("expr");
      expr.add("stat", ctx.getText());
      return expr;
   }
   @Override public ST visitIdcallExpr(EncryptParser.IdcallExprContext ctx) {
      //expr call
      ST expr= templates.getInstanceOf("expr");
      expr.add("stat", ctx.getText());
      return expr;
   }
}
