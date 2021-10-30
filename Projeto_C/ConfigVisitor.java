import org.stringtemplate.v4.*;
import java.io.File;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Method;


public class ConfigVisitor extends EncryptConfigBaseVisitor<ST> {

   protected String target = "java"; 
   private STGroup templates = new STGroupFile(target+".stg");
   private String idCypher = "";
   private Map<String, String> algMap = new HashMap<>(); 
   private List<String> algList = new ArrayList<>();

   @Override public ST visitAssignmentC(EncryptConfigParser.AssignmentCContext ctx) {
      ST assg = templates.getInstanceOf("stats");
      idCypher = ctx.ID().getText();
      Iterator<EncryptConfigParser.ExprCContext> list=ctx.exprC().iterator();

      String[] render = visit(list.next()).render().split("\\|");
      String arg1 = render[0];
      String arg2 = render[1];
      algMap.put(idCypher +"|"+ arg1,arg2);
      if(!arg1.equals("alg") && !algList.contains(idCypher +"|"+ arg1))
         algList.add(idCypher +"|"+ arg1);
      while(list.hasNext()){
         render = visit(list.next()).render().split("\\|");
         arg1 = render[0];
         arg2 = render[1];
         algMap.put(idCypher +"|"+ arg1,arg2);
         if(!arg1.equals("alg") && !algList.contains(idCypher +"|"+ arg1))
            algList.add(idCypher +"|"+ arg1);
      }
      return assg;
   } 

   @Override public ST visitStat2(EncryptConfigParser.Stat2Context ctx) {
      if(ctx.cripto() != null){
          ST stat = templates.getInstanceOf("stats");
          stat.add("stat",visit(ctx.cripto()).render() + ";");
          return stat;
      }
      return  visitChildren(ctx);
   }
      

   @Override public ST visitDeclarationC(EncryptConfigParser.DeclarationCContext ctx) {
      ST cypher=templates.getInstanceOf("stats");
      if(ctx.ID() == null){
         cypher.add("stat", visit(ctx.assignmentC()).render());
      }
      else{
         cypher.add("stat", ctx.ID().getText());
      }
      return cypher;
   }

   @Override public ST visitPut(EncryptConfigParser.PutContext ctx) {
      ST put = templates.getInstanceOf("stats");
      idCypher = ctx.ID().getText();
      String[] render = visit(ctx.exprC()).render().split("\\|");
      String arg1 = idCypher + render[0];
      String arg2 = render[1];
      algMap.put(idCypher +"|"+ arg1,arg2);
      if(!arg1.equals("alg") && !algList.contains(idCypher +"|"+ arg1))
         algList.add(idCypher +"|"+ arg1);
      return put;
   }

   @Override public ST visitAlgExpr(EncryptConfigParser.AlgExprContext ctx){
      ST alg = templates.getInstanceOf("stats");
      alg.add("stat", ctx.n.getText() + "|" + ctx.ID().getText());
      /*try{
         Class cypherAlg = Class.forName(ctx.ID().getText());
         Object T = cypherAlg.newInstance();
         algMap.put(ctx.ID().getText(), cypherAlg);
         Class[] argTypes = new Class[] {String.class};
         Method testMethod = cypherAlg.getMethod("encript", argTypes);
         Object O = testMethod.invoke(T, "boas");
         System.out.println((String) O);
      }catch (Exception e){
         System.out.println(e);
      }*/
      return alg;
   }

   @Override public ST visitModeExpr(EncryptConfigParser.ModeExprContext ctx) {
      ST alg = templates.getInstanceOf("stats");
      alg.add("stat", ctx.n.getText() + "|" + ctx.ID().getText());
      return alg;
   }

   @Override public ST visitKeyExpr(EncryptConfigParser.KeyExprContext ctx) {
      ST alg = templates.getInstanceOf("stats");
      alg.add("stat", ctx.n.getText() + "|" + ctx.INTEGER().getText());
      return alg;
   }

   @Override public ST visitPaddingExpr(EncryptConfigParser.PaddingExprContext ctx) {
      ST alg = templates.getInstanceOf("stats");
      alg.add("stat", ctx.n.getText() + "|" + ctx.ID().getText());
      return alg;
   }
   @Override public ST visitAnyExpr(EncryptConfigParser.AnyExprContext ctx) {
      ST alg = templates.getInstanceOf("stats");
      if(ctx.STRING() == null && ctx.INTEGER() == null)
         alg.add("stat", ctx.ID(0).getText() + "|" + ctx.ID(1).getText());   
      else if (ctx.INTEGER() == null)
         alg.add("stat", ctx.ID(0).getText() + "|" + ctx.STRING().getText().replace("\"",""));
      else
         alg.add("stat", ctx.ID(0).getText() + "|" + ctx.INTEGER().getText());   
      return alg;
   }
   @Override public ST visitLoadAlg(EncryptConfigParser.LoadAlgContext ctx){
      ST cript_initialization = templates.getInstanceOf("init");
      cript_initialization.add("class", ctx.STRING().getText().replace("\"",""));
      cript_initialization.add("name", ctx.ID().getText());
      /*
      ST cipherFunc = templates.getInstanceOf("cipherFunction");
      cipherFunc.add("name", algMap.get("Alg"));
      String statIn = "";
      try{
         File algFile = new File(algMap.get("Alg") + ".java");
         Scanner fileScanner = new Scanner(algFile);
         while(fileScanner.hasNextLine()){
            System.out.println(fileScanner.nextLine());
            statIn = statIn + fileScanner.nextLine() +"\n";
         }
      }catch (Exception e){
         System.out.println("erro");
      }*/
      return cript_initialization;
   }


   @Override public ST visitEncryptAlg(EncryptConfigParser.EncryptAlgContext ctx){
      ST enc = templates.getInstanceOf("fuCall");
      enc.add("class", ctx.ID(0).getText());
      enc.add("cript", "encrypt"); 
      for(String arg : algList){
         if(arg.split("\\|")[0].equals(ctx.ID(0).getText())){
               enc.add("content",algMap.get(arg) + " , ");
         }
      }
      if(ctx.ID(1) != null)
         enc.add("content",ctx.ID(1).getText());
      else if (ctx.STRING() != null)
         enc.add("content",ctx.STRING().getText());
      return enc;
   }


   @Override public ST visitDecryptAlg(EncryptConfigParser.DecryptAlgContext ctx){
      ST dec = templates.getInstanceOf("fuCall");
      dec.add("class", ctx.ID(0).getText());
      dec.add("cript", "decrypt"); 
      for(String arg : algList){
         if(arg.split("\\|")[0].equals(ctx.ID(0).getText())){
               dec.add("content",algMap.get(arg) + " , ");
         }
      }
      if(ctx.ID(1) != null)
         dec.add("content",ctx.ID(1).getText());
      else if (ctx.STRING() != null)
         dec.add("content",ctx.STRING().getText());
      return dec;
   }
}
