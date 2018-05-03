package HandleFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;

public class Read_Data {
	
	private File archivo = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private FileWriter fichero = null;
	private PrintWriter pw = null;
	private String ruta, parent;
	private int size;
	private File name1;
	private File name2;
	
	public Read_Data() {
	}
	
	public void read(String ruta, String parent) {
		this.ruta=ruta;
		this.parent=parent;
		try {
	        archivo = new File (ruta);
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);

	        String linea;
	        while((linea=br.readLine())!=null) {
	        	String[] parts = linea.split("	");
	        	System.out.println("PRIMERA PARTE: "+parts[0]);
	        	System.out.println("SEGUNDA PARTE: "+parts[1]);
	        	if (findFile(parts[1]+".wav",new File(parent))) {
	        		System.out.println("hemos entrado con: " +name1);
	        		System.out.println("hemos entrado con: " +new File(name1.getParent()+"\\"+parts[0]+".wav"));
	        		copyFileUsingFileStreams(name1,new File(name1.getParent()+"\\"+parts[0]+".wav"));
	        	};
	        }
	      }
	      catch(FileNotFoundException e){
	    	  System.out.println("ARCHIVO NO ENCONTRADO");
	      } catch (IOException e) {
			e.printStackTrace();
		}finally{
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	public void write(String[] options) {
		try
        {
			new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Virtual_Totem").mkdirs();
            fichero = new FileWriter("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Virtual_Totem\\Data_Option.txt");
            pw = new PrintWriter(fichero);
            
            for (int x=0; x<options.length; x++) {
                pw.println(options[x]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {

           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
	/*public void findFile(String txt) {
		try {

	        final Pattern pattern = Pattern.compile(
	                "\\A(?=.*" +  txt.replace(";", ")(?=.*") + ").*\\z", 
	                Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	        Files.walkFileTree(Paths.get(parent), new SimpleFileVisitor<Path>() {

	            @Override
	            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
	                    throws IOException {
	                String str = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
	                if (pattern.matcher(str).matches()) {
	                    System.out.println("------------ha coincidido: " +txt);
	                }
	                return FileVisitResult.CONTINUE;
	            }

	        });

	    } catch (IOException e) {
	    	System.out.println("error");
	    }
	}*/
	
	 public Boolean findFile(String name,File file) throws IOException
	    {
		 	boolean exit=false;
	        File[] list = file.listFiles();
	        if(list!=null)
	        for (File fil : list)
	        {
	        	//System.out.println("filgetname: "+fil.getName());
	            if (fil.isDirectory())
	            {
	                exit = findFile(name,fil);
	            }
	            else if (name.equalsIgnoreCase(fil.getName()))
	            {
	                System.out.println("ENCONTRADOOOO: "+fil);
	                exit = true;
	                name1=fil;
	            }
	        }
	        return exit;
	    }
	 
	 private static void copyFileUsingFileStreams(File source, File dest)
		        throws IOException {
		    InputStream input = null;
		    OutputStream output = null;
		    try {
		        input = new FileInputStream(source);
		        output = new FileOutputStream(dest);
		        byte[] buf = new byte[1024];
		        int bytesRead;
		        while ((bytesRead = input.read(buf)) > 0) {
		            output.write(buf, 0, bytesRead);
		        }
		    } finally {
		        input.close();
		        output.close();
		    }
		}


}
