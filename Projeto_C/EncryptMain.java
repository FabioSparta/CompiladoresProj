import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.*;
import static java.lang.System.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;



public class EncryptMain {
   public static void main(String[] args) throws Exception {
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
      // create a lexer that feeds off of input CharStream:
      EncryptLexer lexer = new EncryptLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      EncryptParser parser = new EncryptParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at main rule:
      ParseTree tree = parser.main();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         EncryptCompilerVisitor visitor0 = new EncryptCompilerVisitor();
         visitor0.visit(tree);

               String[] targets = {"java"};
               for(String target: targets)
               {
                  if (!visitor0.validTarget(target))
                  {
                     err.println("ERROR: template group file for target "+target+" not found!");
                     exit(2);
                  }
                  visitor0.setTarget(target);
                  ST code = visitor0.visit(tree);
                  String filename = "Output." + target;
                  try
                  {
                     code.add("name", "Output");
                     PrintWriter pw = new PrintWriter(new File(filename));
                     pw.print(code.render());
                     pw.close();
                  }
                  catch(IOException e)
                  {
                     err.println("ERROR: unable to write in file "+filename);
                     exit(3);
                  }
               }
            





      }
   }
}
