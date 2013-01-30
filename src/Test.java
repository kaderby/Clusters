import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.*;
import java.math.*;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Test {

public static void main(String[] args) throws IOException {

//CONFIGURATION
boolean TypeComplexe = 	true;  	//Choix du type de données (true pour Complexe et false pour Simple)
//Donnees complexes
int AxeX= 				0;		//Choix de l'Axe X (à 1 pour simple, à 19 pour complexes)
int AxeY= 				0;		//Choix de l'Axe Y (à 1 pour simple, à 19 pour complexes)
//Donnees simples
int fichier = 			2;		//Nom du fichier exemple (1 ou 2) dans le cas simple

int k1= 				2; 		//Nombre de Clusters
int distance= 			0;		//Choix de la distance: 0=Eucl 1=L1 2=SimCos 3=SimTan
int iter= 				50;		//Nombre d'iterations



//CHOIX DU FICHIER
Clusterisation c=new Clusterisation();

if(TypeComplexe){c.LoadDatabis("ListeDesMoyennes");
}else{c.LoadData("exemple"+fichier);}

//SOLUTION
c.Solve(k1,distance,iter);

//AFFICHAGE DE LA SOLUTION
XYSeriesCollection dataset = new XYSeriesCollection();
// Create a simple XY chart
for(int i=0;i<k1;i++){
XYSeries series = new XYSeries("Cluster"+" "+i);
Cluster group=c.clusters.get(i);
for(Point p:group.points){
double a=p.valeur.get(AxeX);
double b=p.valeur.get(AxeY);
//if(i==1){
series.add(a,b);
//}
}
dataset.addSeries(series);	
}
JFreeChart chart = ChartFactory.createScatterPlot(
"Clustérisation 2D", // Title
"x", 		// x-axis Label
"y", 		// y-axis Label
dataset, 	// Dataset
PlotOrientation.VERTICAL, // Plot Orientation
true, // Show Legend
true, // Use tooltips
false // Configure chart to generate URLs?
);
//affichage de la fenêtre
ChartFrame frame=new ChartFrame("First",chart);
frame.pack();
frame.setVisible(true);
//sauvegarde du graphique
//ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 700, 500);
}
}