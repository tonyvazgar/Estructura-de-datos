/* Luis Antonio Vázquez García
 * 153675
 * Estructuras de Datos
 * Date creation 26/Agosto/2016
 */
package GUI;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Interfaz extends JFrame implements ActionListener{

	private static final int FRAME_WIDTH = 800;   //Anchura del Frame
    private static final int FRAME_HEIGHT = 400;	//Altura del Frame    
    //Elementos que se van a agregar al frame principal
    private JTextArea textArea;
    private JScrollPane scroll;
    private JMenu fileMenu;
    //Para leer archivos
    File           inFile, outFile;
    FileReader     fr;
    BufferedReader bufReader;
    //JFileChooser chooser;
    FileOutputStream  outFileStream;
    PrintWriter       outStream;
    FileDialog fd;
    int arreglo [];
    int datoEliminar;
    int datoAgregar;
    int MAX;
    int cont;
	/*
	 * Constructor que empieza todos los parametros por defecto:
	 * es decir los menus, etc...
	 */
	public Interfaz(){
		Container contentPane;
		setTitle ("************** GUI Proyecto 1 **************");
        setSize (FRAME_WIDTH, FRAME_HEIGHT);
        contentPane = getContentPane();
        contentPane.setBackground( Color.white );
        textArea = new JTextArea();
        textArea.setEditable(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textArea.setBorder(border);
        setResizable(false);
        scroll= new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBackground(Color.BLACK);
        contentPane.add(scroll);
        crearMenuAcciones();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        
        arreglo = new int[25];
        MAX=25;
        cont=0;
        //for (int i =0;i<arreglo.length;i++){
        //    arreglo[i] = i;   
        //}
	}
	public void crearMenuAcciones(){
		/*
		 * Este método crea los items que va a contener el JMenu y posteriormente van a responder
		 * a las acciones(actionPerformed)
		 */
		fileMenu = 	new JMenu("ACCIONES");
		//-----
		JMenuItem	itemI;
		itemI = new JMenuItem("INSERTAR DATO"); 		//Insertar un dato
		itemI.addActionListener( this );
		fileMenu.add( itemI );
		//-----
		JMenuItem	itemA;
		itemA = new JMenuItem("AGREGAR DATOS DE UN ARCHIVO"); //Agregar de un archivo
		itemA.addActionListener( this );
		fileMenu.add( itemA );
		//----
		JMenuItem	itemE;
		itemE = new JMenuItem("ELIMINAR UN DATO");	//Eliminar un dato
		itemE.addActionListener( this );
		fileMenu.add( itemE );
		//----
		JMenuItem	itemM;
		itemM = new JMenuItem("MOSTRAR DATOS");		//Mostrar los datos
		itemM.addActionListener( this );
		fileMenu.add( itemM );
		//__________
		fileMenu.addSeparator();					//Separador para resaltar "salir"
		//__________
		JMenuItem	item;
		item = new JMenuItem("**SALIR**");			//salir
		item.addActionListener( this );
		fileMenu.add( item );
	}
	public void actionPerformed(ActionEvent event) {
		/*
		 * Este metodo es el que responde a las acciones que crean los items
		 * del menu que se creó anteriormente, se crea una variable de tipo
		 * String para que sea comparada con el nombre del item y este haga 
		 * su trabajo correspondiente...
		 */
		String  menuName;
	    menuName = event.getActionCommand();
	    if(menuName.equals("**SALIR**")){
	    	System.exit(0);
	    }else{
	    	if(menuName.equals("INSERTAR DATO")){
	    		try{
	    			datoAgregar= Integer.parseInt(JOptionPane.showInputDialog(this,"Tu dato:","INSERTAR DATO",JOptionPane.INFORMATION_MESSAGE));
	    			agregarDato();
	    		}catch (Exception e){
	    			e.getStackTrace();
	    		}
	    	}
	    	if(event.getActionCommand().equals("AGREGAR DATOS DE UN ARCHIVO")){
	    		abrirArchivo();
	    	}
	    	
	    	if(event.getActionCommand().equals("ELIMINAR UN DATO")){
	    		try{
	    			datoEliminar= Integer.parseInt(JOptionPane.showInputDialog(this,"Inserta la posición a eliminar","INSERTAR DATO",JOptionPane.INFORMATION_MESSAGE));
	    		}catch(Exception e){
	    			e.getMessage();
	    		}
	    		eliminarDato();
	    	}
	    	if(event.getActionCommand().equals("MOSTRAR DATOS")){
	    		mostrarDatos();
	    	}
	    }
	}
	public void agregarDato(){
		/*
		 * Este método lo tome del de alta_secuencial
		 * el cual arega el dato que deseemos a la posicion
		 * libre que este despues del ultimo usado.
		 */
		if(cont==MAX){
			textArea.append("Ya está lleno el arreglo");
		}else{
			arreglo[cont]=datoAgregar;
			cont++;
		}
		mostrarDatos();
	}
	public void eliminarDato(){
		/*
		 * Este método elimina la posicion deseada y
		 * asigna a esa posición 0
		 */
		//arreglo[datoEliminar]=0;
		//cont--;
		int arreglocopy[];
		int x=0;
		arreglocopy=arreglo;
		try{
			for(int i=0;i<arreglocopy.length; i++){
				if(i==datoEliminar){
					i++;
				}
				arreglo[x]=arreglocopy[i];
				x++;
			}
			arreglo[arreglo.length]=0;
			cont--;
		}catch(Exception e){
			e.getMessage();
		}
		mostrarDatos();
	}
	public void mostrarDatos(){
		/*
		 * Este método muestra todos los datos que hay en el arreglo los
		 * cuales ya fueron agregados anteriormente, de lo contrario los
		 * valores que muestra serán 0,0,0...
		 */
		textArea.append("Los datos en el arreglo son: \n");
		for(int i:arreglo){
			String numero;
			numero= Integer.toString(i);
			textArea.append(" [ "+numero+" ] ,");
			textArea.append("");
			/*
			 * Como convertir INT a String lo encontre en: https://leandrotemperoni.wordpress.com/2012/07/27/como-pasar-de-int-a-string-en-java-y-viceversa/
			 */
		}
		textArea.append("\n");
	}
	private void abrirArchivo(){
		/*
		 * Este método lee un archivo, el cual lo tome del
		 * ejemplo que está en blackboard...
		 */
    	int reply,i;
    	String line, archivo, doc, path;
    	doc = "";
        try {
             fd = new FileDialog(this, "Selecciona archivo para cargar datos", FileDialog.LOAD);
             fd.setVisible(true);
             path = fd.getDirectory();
             archivo = fd.getFile();
                if ((path == null) || (archivo == null)) return;
	         doc = path + archivo;
                textArea.append(doc);
	         	//System.out.println("RUTA = "+doc);
                 
             inFile     = new File(doc);
             fr = new FileReader(inFile);
             bufReader  = new BufferedReader(fr);
             
             textArea.setText("");
             i=cont;
            while (true) {
                line = bufReader.readLine();
                if (line == null) {
                    return;
                }
                arreglo[i]=Integer.parseInt(line);
                i = i+1;
                cont++;
                textArea.append("  "+line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in opening a file: \n"+ e.getMessage());
      }
        finally{
        for(i=0;i<arreglo.length;i++)
        	textArea.append("\n"+"Arreglo[ "+i+" ] = "+arreglo[i]+"\n");
        	/*
        	 * Lo del "\n" que no sabia lo tome de:
        	 * http://www.lawebdelprogramador.com/foros/Java/937289-Salto-de-linea-del-TextArea.html
        	 */
        }
    }
	public static void main(String[] args){
		Interfaz ventana = new Interfaz();
		ventana.setVisible(true);
	}
}