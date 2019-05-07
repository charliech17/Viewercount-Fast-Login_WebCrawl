package YoutubeWebCrawl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import YoutubeWebCrawl.yotubeGUI.btn1Listener;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VerseCrawlGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JComboBox Box;
	private JTextArea lb1;
	private String bookk[]= {"1&f","2&f","3&f","4&f","5&f","6&f","7&f","8&f","9&f","10&f","11&f","12&f","13&f","14&f","15&f","16&f","17&f","18&f","19&f","20&f","21&f","22&f","23&f","24&f","25&f","26&f","27&f","28&f","29&f","30&f","31&f","32&f","33&f","34&f","35&f","36&f","37&f","38&f","39&f","40&f","41&f","42&f","43&f","44&f","45&f","46&f","47&f","48&f","49&f","50&f","51&f","52&f","53&f","54&f","55&f","56&f","57&f","58&f","59&f","60&f","61&f","62&f","63&f","64&f","65&f","66&f"};
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerseCrawlGUI frame = new VerseCrawlGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VerseCrawlGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Box = new JComboBox();
		Box.setBounds(10, 74, 66, 30);
		contentPane.add(Box);
		String book[]= {"創","出","利","民","申","書","士","得","撒上","撒下","王上","王下","代上","代下","拉","尼","斯","伯","詩","箴","傳","歌","賽","耶","哀","結","但","何","珥","摩","俄","拿","彌","鴻","哈","番","該","亞","瑪","太","可","路","約","徒","羅","林前","林後","加","弗","腓","西","帖前","帖後","提前","提後","多","門","來","雅","彼前","彼後","約一","約二","約三","猶","啟"} ;
		for(int i=0 ;i<book.length ;i++){Box.addItem(book[i]);}
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("\u66F8\u5377");
		lblNewLabel.setBounds(30, 37, 46, 15);
		contentPane.add(lblNewLabel);
		
		tf1 = new JTextField();
		tf1.setBounds(126, 79, 96, 21);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setBounds(265, 79, 96, 21);
		contentPane.add(tf2);
		tf2.setColumns(10);
		
		JLabel label = new JLabel("\u7AE0");
		label.setBounds(153, 37, 46, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u7BC0");
		label_1.setBounds(293, 37, 46, 15);
		contentPane.add(label_1);
		
		lb1 = new JTextArea("New label");
		lb1.setBounds(10, 187, 292, 61);
		contentPane.add(lb1);
		
		Button btn1 = new Button("\u67E5\u8A62");
		btn1.setBounds(364, 158, 76, 23);
		contentPane.add(btn1);
		
		btn1.addActionListener((ActionListener)new btn1Listener() );
	}
	
	class btn1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		int book= Box.getSelectedIndex();	
		String bookkk=bookk[book];
		String c=tf1.getText();
		String v=tf2.getText();
		  versefind(bookkk,c,v);	
			
			
		}

		private void versefind(String bookkk,String c,String v) {
			WebDriver driver = null;
			driver = new ChromeDriver();   
		    driver.get("http://www.recoveryversion.com.tw/Style0A/026/read_List.php?f_BookNo="+bookkk+"_ChapterNo="+c);
		    
		    JavascriptExecutor js = (JavascriptExecutor)driver;	
		    waitForLoad(driver);
		    

		    String html = js.executeScript("return document.body.innerHTML;").toString();
		    org.jsoup.nodes.Document doc= Jsoup.parse(html);
		    Elements ids = doc.select("a[name=\""+v+"\"]");
		       
	        String verse=null;
		    for(Element id:ids) {
	    	       verse=id.parent().parent().text();
	    		   
	    		   String versef=verse.replaceAll("\\d+", "");
	    		   lb1.setText(Box.getSelectedItem().toString()+c+":"+v+":"+versef.substring(1));
	    		   System.out.println(versef);
		    }
		    
		    driver.quit();
			
		}
		
		public void waitForLoad(WebDriver driver) {
	        ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, 50);
	        wait.until(pageLoadCondition);
	        
	 }
		
	}
	
	
	
	
}
