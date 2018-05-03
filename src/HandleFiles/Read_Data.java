package HandleFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class Read_Data{
	
	private File archivo = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private int copiados, existentes, lines;
	private File name1;
	private Main main;
	
	public Read_Data(Main main) {
		this.main=main;
		lines=0;
	}
	
	
	
	public void read(String ruta, String parent) {
		Thread t1 = new Thread(new Runnable() {
	         public void run() {
	     		copiados=0;
	     		existentes=0;
	     		
	     		try {
	     	        archivo = new File (ruta);
	     	        fr = new FileReader (archivo);
	     	        br = new BufferedReader(fr);
	     	        int x=0;
	     	        String linea;
	     	        while((linea=br.readLine())!=null) {
	     	        	String[] parts = linea.split("	");
	     	        	System.out.println("NOMBRE DEL AUDIO:   "+parts[0]);
	     	        	System.out.println("ARCHIVO ORIGEN:     "+parts[1]);
	     	        	if (findFile(parts[1]+".wav",new File(parent))) {
	     	        		File dest = new File(name1.getParent()+"\\"+parts[0]+".wav");
	     	        		System.out.println("ARCHIVO ENCONTRADO: " +name1);
	     	        		if(!dest.exists()) {
	     	        			copyFileUsingFileStreams(name1,dest);
	     	        			copiados++;
	     	        		}
	     	        		else {
	     	        			existentes++;
	     	        		}
	     	        	};
	     	        	System.out.println("--------------------------------------------------------------");
	     	        	
	     	        	float percent = (float)Math.floor(x * 100f / lines);
	     	        	main.changebtn(percent);

	     	           x++;   
	     	        }
	     	       main.changebtn((float) 100);
	     	       main.activateBtn();
	     	        JOptionPane.showMessageDialog(null,"Numero de archivos copiados: " +copiados+ "\n"+ "Número de archivos ya existentes: " + existentes);
	     	        
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
	    });  
	    t1.start();
		
	}
	
	public void countLines(String ruta) {
		try {
	        archivo = new File (ruta);
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);

	        while((br.readLine())!=null) {
	        	lines++;
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
	
	 public Boolean findFile(String name,File file) throws IOException
	    {
		 	boolean exit=false;
	        File[] list = file.listFiles();
	        if(list!=null)
	        for (File fil : list)
	        {
	            if (fil.isDirectory())
	            {
	                exit = findFile(name,fil);
	            }
	            else if (name.equalsIgnoreCase(fil.getName()))
	            {
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
