import java.awt.Color; //Import Libraries
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Viewer {
    public static String homeCSVFilePath; //Setup variables to input into the elements for viewing
	public static String awayCSVFilePath;
	public static ArrayList<String> hnumberName = new ArrayList<String>();
	public static ArrayList<String> hteam = new ArrayList<String>();
	public static ArrayList<Integer> hMake2 = new ArrayList<Integer>();
	public static ArrayList<Integer> hTotal2 = new ArrayList<Integer>();
	public static ArrayList<Integer> hMake3 = new ArrayList<Integer>();
	public static ArrayList<Integer> hTotal3 = new ArrayList<Integer>();
	public static ArrayList<Integer> hMakeFoul = new ArrayList<Integer>();
	public static ArrayList<Integer> hTotalFoul = new ArrayList<Integer>();
    public static ArrayList<String> anumberName = new ArrayList<String>();
	public static ArrayList<String> ateam = new ArrayList<String>();
	public static ArrayList<Integer> aMake2 = new ArrayList<Integer>();
	public static ArrayList<Integer> aTotal2 = new ArrayList<Integer>();
	public static ArrayList<Integer> aMake3 = new ArrayList<Integer>();
	public static ArrayList<Integer> aTotal3 = new ArrayList<Integer>();
	public static ArrayList<Integer> aMakeFoul = new ArrayList<Integer>();
	public static ArrayList<Integer> aTotalFoul = new ArrayList<Integer>();

    public static void main (String[] args) throws IOException {
        //Create an image to use as the logo
        ImageIcon icon = new ImageIcon("JCD LIVE LOGO 1 .png");
		Image logo = icon.getImage();
		
		//Create a JFrame to Select a File location
		JFrame selectFile = new JFrame ();
		selectFile.setTitle("Stat Viewer: Select Files");
		selectFile.setSize(500, 300);
		selectFile.setIconImage(logo);
		selectFile.setLayout(null);
		selectFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create Components for the Select File Frame
		JLabel homeInstructions = new JLabel ("Please Enter the File path of where the .csv home stat file is located:");
		homeInstructions.setBounds(20, 20, 500, 15);
		selectFile.add(homeInstructions);
		JTextField homeFilePath = new JTextField ("C:/Users/LiveStream/Desktop/homefullstats.csv");
		homeFilePath.setBounds(20, 40, 300, 20);
		selectFile.add(homeFilePath);
		JButton homeBrowse = new JButton ("Browse");
		homeBrowse.setBounds(340, 40, 100, 20);
		selectFile.add(homeBrowse);
		
		JLabel awayInstructions = new JLabel ("Please Enter the File path of where the .csv away stat file is located:");
		awayInstructions.setBounds(20, 100, 500, 15);
		selectFile.add(awayInstructions);
		JTextField awayFilePath = new JTextField ("C:/Users/LiveStream/Desktop/awayfullstats.csv");
		awayFilePath.setBounds(20, 120, 300, 20);
		selectFile.add(awayFilePath);
		JButton awayBrowse = new JButton ("Browse");
		awayBrowse.setBounds(340, 120, 100, 20);
		selectFile.add(awayBrowse);
		
		JButton enter = new JButton ("Go to Stat Viewer");
		enter.setBounds(125, 200, 250, 20);
		selectFile.add(enter);
		JFileChooser chooser = new JFileChooser();
		selectFile.setVisible(true);

		//Get file paths
	    homeBrowse.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Seperated Values (.csv)", "csv");
	    	        chooser.setFileFilter(filter);
	    	    int returnVal = chooser.showOpenDialog(selectFile);
	    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	       homeFilePath.setText(chooser.getSelectedFile().getAbsolutePath());
	    	    }
	    	}  
	    }); 
	    awayBrowse.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Seperated Values (.csv)", "csv");
	    	        chooser.setFileFilter(filter);
	    	    int returnVal = chooser.showOpenDialog(selectFile);
	    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	       awayFilePath.setText(chooser.getSelectedFile().getAbsolutePath());
	    	    }
	    	}  
	    }); 
		
		JFrame frame = new JFrame();
		frame.setTitle("Stat Controller");
		frame.setSize(1280,720);
		frame.setLayout(null);
		frame.setIconImage(logo);
		frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList hlist = new JList(hnumberName.toArray()); //data has type Object[]
	    hlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    hlist.setLayoutOrientation(JList.VERTICAL);
	    hlist.setVisibleRowCount(-1);
	    JScrollPane hscrollPane = new JScrollPane();
	    hscrollPane.setViewportView(hlist);
	    hlist.setLayoutOrientation(JList.VERTICAL);
	    hscrollPane.setBounds(10,40,250,400);
	    frame.add(hscrollPane);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList alist = new JList(hnumberName.toArray()); //data has type Object[]
	    alist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    alist.setLayoutOrientation(JList.VERTICAL);
	    alist.setVisibleRowCount(-1);
	    JScrollPane ascrollPane = new JScrollPane();
	    ascrollPane.setViewportView(alist);
	    alist.setLayoutOrientation(JList.VERTICAL);
	    ascrollPane.setBounds(650,40,250,400);
	    frame.add(ascrollPane);

		enter.addActionListener(new ActionListener(){  
	    	@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				File homefile = new File (homeFilePath.getText());
	    		try {
					homefile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				File awayfile = new File (awayFilePath.getText());
	    		try {
					awayfile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				selectFile.setVisible(false);
	    		frame.setVisible(true);
	    		homeCSVFilePath = homeFilePath.getText();
	    		awayCSVFilePath = awayFilePath.getText();
	    		
	    		//Read And Load the CSV Home File
	    	    if (new File (homeCSVFilePath).exists()) {
	    	    	BufferedReader reader;
	    			try {
	    				reader = new BufferedReader(new FileReader(
	    						homeCSVFilePath));
	    				String line = reader.readLine();
	    				while (line != null) {
	    					String[] splitLine = line.split(",");
	    					hteam.add(splitLine[2]);
	    					hMake2.add(Integer.parseInt(splitLine[3]));
	    					hTotal2.add(Integer.parseInt(splitLine[4]));
	    					hMake3.add(Integer.parseInt(splitLine[5]));
	    					hTotal3.add(Integer.parseInt(splitLine[6]));
	    					hMakeFoul.add(Integer.parseInt(splitLine[7]));
	    					hTotalFoul.add(Integer.parseInt(splitLine[8]));
	    					hnumberName.add ("<html><font color=\"red\">#"+splitLine[1]+" - </font></font color=\"black\">"+splitLine[0]+"</font></html>");
							hlist.setListData(hnumberName.toArray());
							// read next line
	    					line = reader.readLine();
	    				}
	    				reader.close();
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	    	    }

                //Read And Load the CSV Away File
                if (new File (awayCSVFilePath).exists()) {
	    	    	BufferedReader reader;
	    			try {
	    				reader = new BufferedReader(new FileReader(
	    						awayCSVFilePath));
	    				String line = reader.readLine();
	    				while (line != null) {
	    					String[] splitLine = line.split(",");
	    					ateam.add(splitLine[2]);
	    					aMake2.add(Integer.parseInt(splitLine[3]));
	    					aTotal2.add(Integer.parseInt(splitLine[4]));
	    					aMake3.add(Integer.parseInt(splitLine[5]));
	    					aTotal3.add(Integer.parseInt(splitLine[6]));
	    					aMakeFoul.add(Integer.parseInt(splitLine[7]));
	    					aTotalFoul.add(Integer.parseInt(splitLine[8]));
	    					anumberName.add ("<html><font color=\"red\">#"+splitLine[1]+" - </font></font color=\"black\">"+splitLine[0]+"</font></html>");
							alist.setListData(anumberName.toArray());
							// read next line
	    					line = reader.readLine();
	    				}
	    				reader.close();
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	    	    }
	    	}  
	    });

		JLabel hpercentage2 = new JLabel ("-");
        hpercentage2.setBounds(400, 95, 100, 20);
        frame.add(hpercentage2);
        
        JLabel hlabel2 = new JLabel ("2 Point Shots:");
        hlabel2.setBounds(280, 65, 200, 20);
        frame.add(hlabel2);
        
        JLabel hmake2 = new JLabel("-");
        hmake2.setBounds(280, 95, 25, 20);
        frame.add(hmake2);
        
        JLabel hslash2 = new JLabel ("/");
        hslash2.setBounds(315, 95, 25, 20);
        frame.add(hslash2);
        
        JLabel htotal2 = new JLabel("-");
        htotal2.setBounds(330, 95, 25, 20);
        frame.add(htotal2);
        
        JLabel hpercentage3 = new JLabel ("-");
        hpercentage3.setBounds(400, 175, 100, 20);
        frame.add(hpercentage3);
        
        JLabel hlabel3 = new JLabel ("3 Point Shots:");
        hlabel3.setBounds(280, 145, 200, 20);
        frame.add(hlabel3);
        
        JLabel hmake3 = new JLabel("-");
        hmake3.setBounds(280, 175, 25, 20);
        frame.add(hmake3);
        
        JLabel hslash3 = new JLabel ("/");
        hslash3.setBounds(315, 175, 25, 20);
        frame.add(hslash3);
        
        JLabel htotal3 = new JLabel("-");
        htotal3.setBounds(330, 175, 25, 20);
        frame.add(htotal3);
        
        JLabel hpercentageFoul = new JLabel ("-");
        hpercentageFoul.setBounds(400, 265, 100, 20);
        frame.add(hpercentageFoul);
        
        JLabel hlabelFoul = new JLabel ("Foul Shots:");
        hlabelFoul.setBounds(280, 235, 200, 20);
        frame.add(hlabelFoul);
        
        JLabel hmakeFoul = new JLabel("-");
        hmakeFoul.setBounds(280, 265, 25, 20);
        frame.add(hmakeFoul);
        
        JLabel hslashFoul = new JLabel ("/");
        hslashFoul.setBounds(315, 265, 25, 20);
        frame.add(hslashFoul);
        
        JLabel htotalFoul = new JLabel("-");
        htotalFoul.setBounds(330, 265, 25, 20);
        frame.add(htotalFoul);

		JLabel apercentage2 = new JLabel ("-");
        apercentage2.setBounds(1040, 95, 100, 20);
        frame.add(apercentage2);
        
        JLabel alabel2 = new JLabel ("2 Point Shots:");
        alabel2.setBounds(920, 65, 200, 20);
        frame.add(alabel2);
        
        JLabel amake2 = new JLabel("-");
        amake2.setBounds(920, 95, 25, 20);
        frame.add(amake2);
        
        JLabel aslash2 = new JLabel ("/");
        aslash2.setBounds(955, 95, 25, 20);
        frame.add(aslash2);
        
        JLabel atotal2 = new JLabel("-");
        atotal2.setBounds(970, 95, 25, 20);
        frame.add(atotal2);
        
        JLabel apercentage3 = new JLabel ("-");
        apercentage3.setBounds(1040, 175, 100, 20);
        frame.add(apercentage3);
        
        JLabel alabel3 = new JLabel ("3 Point Shots:");
        alabel3.setBounds(920, 145, 200, 20);
        frame.add(alabel3);
        
        JLabel amake3 = new JLabel("-");
        amake3.setBounds(920, 175, 25, 20);
        frame.add(amake3);
        
        JLabel aslash3 = new JLabel ("/");
        aslash3.setBounds(955, 175, 25, 20);
        frame.add(aslash3);
        
        JLabel atotal3 = new JLabel("-");
        atotal3.setBounds(970, 175, 25, 20);
        frame.add(atotal3);
        
        JLabel apercentageFoul = new JLabel ("-");
        apercentageFoul.setBounds(1040, 265, 100, 20);
        frame.add(apercentageFoul);
        
        JLabel alabelFoul = new JLabel ("Foul Shots:");
        alabelFoul.setBounds(920, 235, 200, 20);
        frame.add(alabelFoul);
        
        JLabel amakeFoul = new JLabel("-");
        amakeFoul.setBounds(920, 265, 25, 20);
        frame.add(amakeFoul);
        
        JLabel aslashFoul = new JLabel ("/");
        aslashFoul.setBounds(955, 265, 25, 20);
        frame.add(aslashFoul);
        
        JLabel atotalFoul = new JLabel("-");
        atotalFoul.setBounds(970, 265, 25, 20);
        frame.add(atotalFoul);

		JLabel hStatsLabel = new JLabel("Home Stats");
		hStatsLabel.setBounds(10, 10, 200, 20);
		frame.add(hStatsLabel);

		JLabel aStatsLabel = new JLabel("Away Stats");
		aStatsLabel.setBounds(650, 10, 200, 20);
		frame.add(aStatsLabel);

		JLabel aTeamStatsLabel = new JLabel("Team Stats");
		aTeamStatsLabel.setBounds(650, 500, 200, 20);
		frame.add(aTeamStatsLabel);

		JLabel hTeamStatsLabel = new JLabel("Team Stats");
		hTeamStatsLabel.setBounds(10, 500, 200, 20);
		frame.add(hTeamStatsLabel);

		JLabel hTeam2 = new JLabel(sum(hMake2)+"/"+sum(hTotal2));
		hTeam2.setBounds(10, 600, 100, 20);
		frame.add(hTeam2);

		JLabel hTeamPercentage2 = new JLabel(Math.round(((double)sum(hMake2)/(double)sum(hTotal2))*100)+"%");
		hTeamPercentage2.setBounds(110, 600, 50, 20);
		frame.add(hTeamPercentage2);

		JLabel hTeam3 = new JLabel(sum(hMake3)+"/"+sum(hTotal3));
		hTeam3.setBounds(160, 600, 100, 20);
		frame.add(hTeam3);

		JLabel hTeamPercentage3 = new JLabel(Math.round(((double)sum(hMake3)/(double)sum(hTotal3))*100)+"%");
		hTeamPercentage3.setBounds(260, 600, 50, 20);
		frame.add(hTeamPercentage3);

		JLabel hTeamFoul = new JLabel(sum(hMakeFoul)+"/"+sum(hTotalFoul));
		hTeamFoul.setBounds(310, 600, 100, 20);
		frame.add(hTeamFoul);

		JLabel hTeamPercentageFoul = new JLabel(Math.round(((double)sum(hMakeFoul)/(double)sum(hTotalFoul))*100)+"%");
		hTeamPercentageFoul.setBounds(410, 600, 200, 20);
		frame.add(hTeamPercentageFoul);

		JLabel aTeam2 = new JLabel(sum(aMake2)+"/"+sum(aTotal2));
		aTeam2.setBounds(640, 600, 100, 20);
		frame.add(aTeam2);

		JLabel aTeamPercentage2 = new JLabel(Math.round(((double)sum(aMake2)/(double)sum(aTotal2))*100)+"%");
		aTeamPercentage2.setBounds(740, 600, 50, 20);
		frame.add(aTeamPercentage2);

		JLabel aTeam3 = new JLabel(sum(aMake3)+"/"+sum(aTotal3));
		aTeam3.setBounds(790, 600, 100, 20);
		frame.add(aTeam3);

		JLabel aTeamPercentage3 = new JLabel(Math.round(((double)sum(aMake3)/(double)sum(aTotal3))*100)+"%");
		aTeamPercentage3.setBounds(890, 600, 50, 20);
		frame.add(aTeamPercentage3);

		JLabel aTeamFoul = new JLabel(sum(aMakeFoul)+"/"+sum(aTotalFoul));
		aTeamFoul.setBounds(940, 600, 100, 20);
		frame.add(aTeamFoul);

		JLabel aTeamPercentageFoul = new JLabel(Math.round(((double)sum(aMakeFoul)/(double)sum(aTotalFoul))*100)+"%");
		aTeamPercentageFoul.setBounds(1040, 600, 50, 20);
		frame.add(aTeamPercentageFoul);

		JLabel hPoint2 = new JLabel ("2-Points");
		hPoint2.setBounds(10, 550, 150, 20);
		frame.add(hPoint2);

		JLabel hPoint3 = new JLabel ("3-Points");
		hPoint3.setBounds(160, 550, 150, 20);
		frame.add(hPoint3);

		JLabel hPointFoul = new JLabel ("Foul-Shots");
		hPointFoul.setBounds(310, 550, 150, 20);
		frame.add(hPointFoul);

		JLabel aPoint2 = new JLabel ("2-Points");
		aPoint2.setBounds(640, 550, 150, 20);
		frame.add(aPoint2);

		JLabel aPoint3 = new JLabel ("3-Points");
		aPoint3.setBounds(790, 550, 150, 20);
		frame.add(aPoint3);

		JLabel aPointFoul = new JLabel ("Foul-Shots");
		aPointFoul.setBounds(940, 550, 150, 20);
		frame.add(aPointFoul);

		JButton refresh = new JButton ("Refresh");
		refresh.setBounds(365,20, 150, 20);
		frame.add(refresh);

		//Update the interface when a player is selected
		hlist.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				hmake2.setText(""+hMake2.get(hlist.getSelectedIndex()));
				htotal2.setText(""+hTotal2.get(hlist.getSelectedIndex()));
				hmake3.setText(""+hMake3.get(hlist.getSelectedIndex()));
				htotal3.setText(""+hTotal3.get(hlist.getSelectedIndex()));
				hmakeFoul.setText(""+hMakeFoul.get(hlist.getSelectedIndex()));
				htotalFoul.setText(""+hTotalFoul.get(hlist.getSelectedIndex()));
				hpercentage2.setText((int)(((double)hMake2.get(hlist.getSelectedIndex()))/(hTotal2.get(hlist.getSelectedIndex()))*100) + "%");
		        hpercentage3.setText((int)(((double)hMake3.get(hlist.getSelectedIndex()))/(hTotal3.get(hlist.getSelectedIndex()))*100) + "%");
		        hpercentageFoul.setText((int)(((double)hMakeFoul.get(hlist.getSelectedIndex()))/(hTotalFoul.get(hlist.getSelectedIndex()))*100) + "%");
			}
        });


		alist.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				amake2.setText(""+aMake2.get(alist.getSelectedIndex()));
				atotal2.setText(""+aTotal2.get(alist.getSelectedIndex()));
				amake3.setText(""+aMake3.get(alist.getSelectedIndex()));
				atotal3.setText(""+aTotal3.get(alist.getSelectedIndex()));
				amakeFoul.setText(""+aMakeFoul.get(alist.getSelectedIndex()));
				atotalFoul.setText(""+aTotalFoul.get(alist.getSelectedIndex()));
				apercentage2.setText((int)(((double)aMake2.get(alist.getSelectedIndex()))/(aTotal2.get(alist.getSelectedIndex()))*100) + "%");
		        apercentage3.setText((int)(((double)aMake3.get(alist.getSelectedIndex()))/(aTotal3.get(alist.getSelectedIndex()))*100) + "%");
		        apercentageFoul.setText((int)(((double)aMakeFoul.get(alist.getSelectedIndex()))/(aTotalFoul.get(alist.getSelectedIndex()))*100) + "%");
			}
        });
		refresh.addActionListener(new ActionListener(){  
	    	@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				hnumberName = new ArrayList<String>();
				hteam = new ArrayList<String>();
				hMake2 = new ArrayList<Integer>();
				hTotal2 = new ArrayList<Integer>();
				hMake3 = new ArrayList<Integer>();
				hTotal3 = new ArrayList<Integer>();
				hMakeFoul = new ArrayList<Integer>();
				hTotalFoul = new ArrayList<Integer>();
				anumberName = new ArrayList<String>();
				ateam = new ArrayList<String>();
				aMake2 = new ArrayList<Integer>();
				aTotal2 = new ArrayList<Integer>();
				aMake3 = new ArrayList<Integer>();
				aTotal3 = new ArrayList<Integer>();
				aMakeFoul = new ArrayList<Integer>();
				aTotalFoul = new ArrayList<Integer>();
				File homefile = new File (homeFilePath.getText());
	    		try {
					homefile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				File awayfile = new File (awayFilePath.getText());
	    		try {
					awayfile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				selectFile.setVisible(false);
	    		frame.setVisible(true);
	    		homeCSVFilePath = homeFilePath.getText();
	    		awayCSVFilePath = awayFilePath.getText();
	    		
	    		//Read And Load the CSV Home File
	    	    if (new File (homeCSVFilePath).exists()) {
	    	    	BufferedReader reader;
	    			try {
	    				reader = new BufferedReader(new FileReader(
	    						homeCSVFilePath));
	    				String line = reader.readLine();
	    				while (line != null) {
	    					String[] splitLine = line.split(",");
	    					hteam.add(splitLine[2]);
	    					hMake2.add(Integer.parseInt(splitLine[3]));
	    					hTotal2.add(Integer.parseInt(splitLine[4]));
	    					hMake3.add(Integer.parseInt(splitLine[5]));
	    					hTotal3.add(Integer.parseInt(splitLine[6]));
	    					hMakeFoul.add(Integer.parseInt(splitLine[7]));
	    					hTotalFoul.add(Integer.parseInt(splitLine[8]));
	    					hnumberName.add ("<html><font color=\"red\">#"+splitLine[1]+" - </font></font color=\"black\">"+splitLine[0]+"</font></html>");
							hlist.setListData(hnumberName.toArray());
							hTeam2.setText(sum(hMake2)+"/"+sum(hTotal2));
							hTeamPercentage2.setText(Math.round(((double)sum(hMake2)/(double)sum(hTotal2))*100)+"%");
							hTeam3.setText(sum(hMake3)+"/"+sum(hTotal3));
							hTeamPercentage3.setText(Math.round(((double)sum(hMake3)/(double)sum(hTotal3))*100)+"%");
							hTeamFoul.setText(sum(hMakeFoul)+"/"+sum(hTotalFoul));
							hTeamPercentageFoul.setText(Math.round(((double)sum(hMakeFoul)/(double)sum(hTotalFoul))*100)+"%");
							// read next line
							line = reader.readLine();
	    				}
	    				reader.close();
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	    	    }

                //Read And Load the CSV Away File
                if (new File (awayCSVFilePath).exists()) {
	    	    	BufferedReader reader;
	    			try {
	    				reader = new BufferedReader(new FileReader(
	    						awayCSVFilePath));
	    				String line = reader.readLine();
	    				while (line != null) {
	    					String[] splitLine = line.split(",");
	    					ateam.add(splitLine[2]);
	    					aMake2.add(Integer.parseInt(splitLine[3]));
	    					aTotal2.add(Integer.parseInt(splitLine[4]));
	    					aMake3.add(Integer.parseInt(splitLine[5]));
	    					aTotal3.add(Integer.parseInt(splitLine[6]));
	    					aMakeFoul.add(Integer.parseInt(splitLine[7]));
	    					aTotalFoul.add(Integer.parseInt(splitLine[8]));
	    					anumberName.add ("<html><font color=\"red\">#"+splitLine[1]+" - </font></font color=\"black\">"+splitLine[0]+"</font></html>");
							alist.setListData(anumberName.toArray());
							aTeam2.setText(sum(aMake2)+"/"+sum(aTotal2));
							aTeamPercentage2.setText(Math.round(((double)sum(aMake2)/(double)sum(aTotal2))*100)+"%");
							aTeam3.setText(sum(aMake3)+"/"+sum(aTotal3));
							aTeamPercentage3.setText(Math.round(((double)sum(aMake3)/(double)sum(aTotal3))*100)+"%");
							aTeamFoul.setText(sum(aMakeFoul)+"/"+sum(aTotalFoul));
							aTeamPercentageFoul.setText(Math.round(((double)sum(aMakeFoul)/(double)sum(aTotalFoul))*100)+"%");
							// read next line
	    					line = reader.readLine();
	    				}
	    				reader.close();
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	    	    }
	    	}  
	    });
    }
	//Add a list of numbers
	public static int sum (ArrayList <Integer> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		return sum;
		
	}

}
