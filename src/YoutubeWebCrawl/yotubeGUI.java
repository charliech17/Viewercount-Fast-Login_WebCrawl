package YoutubeWebCrawl;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;


public class yotubeGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JPasswordField pef;
	private JButton btn1,btn2;
	WebElement we1,we2;
	String username, passward;
	private static WebDriver driver = null;
	private int count=0;


//main code
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {framestart();} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

//GUI Setting
	public yotubeGUI() {
		Thread t1=new Mythread1();
		t1.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb1 = new JLabel("Youtube \u767B\u5165\u7CFB\u7D71");
		lb1.setForeground(Color.BLACK);
		lb1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 22));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(10, 10, 202, 24);
		//lb1.setBorder(BorderFactory.createLineBorder(Color.decode("#0000FF")));
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("\u5E33\u865F");
		lb2.setForeground(Color.BLACK);
		lb2.setBackground(Color.decode("#0000FF"));
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setFont(new Font("\u5FAE\u8EDF\u6B63\u9ED1\u9AD4 Light", lb2.getFont().getStyle() | Font.BOLD, 16));
		lb2.setBounds(10, 64, 46, 20);
		lb2.setOpaque(true);
		contentPane.add(lb2);
		
		JLabel lb3 = new JLabel("\u5BC6\u78BC");
		lb3.setBackground(Color.decode("#0000FF"));
		lb3.setForeground(Color.BLACK);
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		lb3.setFont(new Font("\u5FAE\u8EDF\u6B63\u9ED1\u9AD4 Light", lb3.getFont().getStyle() | Font.BOLD, 16));
		lb3.setBounds(10, 124, 46, 20);
		lb3.setOpaque(true);
		contentPane.add(lb3);
		
		tf1 = new JTextField();
		tf1.setBounds(92, 63, 96, 21);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		pef = new JPasswordField();
		pef.setBounds(92, 123, 96, 21);
		contentPane.add(pef);
		
		btn1 = new JButton("\u767B\u5165");
		btn1.setBounds(10, 178, 87, 23);
		contentPane.add(btn1);
		
		btn2 = new JButton("\u6E05\u9664");
		btn2.setBounds(125, 178, 87, 23);
		contentPane.add(btn2);
		
		
		btn1.addActionListener((ActionListener)new btn1Listener() );
		btn2.addActionListener((ActionListener)new btn2Listener());
		keypress();
		
	
		
	}

//Handle JFrame events	
	protected static void framestart() {
		yotubeGUI frame = new yotubeGUI();
		frame.setVisible(true);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				driver.quit();
			}
		});
		
	}

//catch Keyboard chars 	
   private void keypress() {
	  
	   pef.addKeyListener(new KeyAdapter()

     	{

     		public void keyPressed(KeyEvent key)

	      	{

	      		if(key.getKeyChar() == KeyEvent.VK_ENTER)

	      			btn1.doClick();

	      	}

     	});

     	
		btn1.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent key) {
				
				if(key.getKeyChar() == KeyEvent.VK_ENTER)

	      			btn1.doClick();
			}
			
			});
		
		btn2.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent key) {
				
				if(key.getKeyChar() == KeyEvent.VK_ENTER)

	      			btn2.doClick();
			}
			
			});
	
   }
//The Thread run for printing the count-View and entering the login page. 	
	class Mythread1 extends Thread{
		public void run() {
			
			driver = new ChromeDriver();   
		    driver.get("https://www.youtube.com/watch?v=QSR6hilYP4Y&t=17s");
		    
		   
		    JavascriptExecutor js = (JavascriptExecutor)driver;
		   
		    waitForLoad(driver);
		    String theTextIWant=null;
		    
		    for(int i=0;i<=10;i++) {
		    	 try {
				    	Thread.sleep(250);
			    		theTextIWant = js.executeScript("return arguments[0].innerHTML;",driver.findElement(By.xpath("//*[@id=\"count\"]/yt-view-count-renderer/span[1]"))).toString();
			    		Thread.sleep(250);
			    		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"buttons\"]/ytd-button-renderer/a")));
			    		if(theTextIWant!=null) {break;}	
				    }catch(Exception ex) {}  	
		    }
		   
		    System.out.println(theTextIWant);
		    
		}
	}
	
//Wait data loading finish
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
	
//Handle "btn1" events	
	class btn1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		//login	
			if(count==0) {
				username=tf1.getText();
				passward=new String(pef.getPassword());
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				
				String theTextIWant=null;
				for(int i=0;i<=10;i++) {
					
					try {
						
					    if(js.executeScript("return document.readyState").toString().equals("complete")) {
					    	Thread.sleep(250);
						    js.executeScript("arguments[1].value = arguments[0]; ",username,driver.findElement(By.xpath("//*[@id=\"identifierId\"]")));
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"identifierNext\"]")));
							Thread.sleep(500);
							theTextIWant = js.executeScript("return arguments[0].innerHTML;",driver.findElement(By.xpath("//*[@id=\"headingText\"]/content"))).toString();
							js.executeScript("arguments[1].value = arguments[0]; ",passward,driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"passwordNext\"]")));
							if(theTextIWant!=null) {break;}
								
					    }
					
					}catch(Exception ex) {}
		 

				}
				
				pef.setText("");
				passward="";
				if(theTextIWant!=null) {
					count+=1;
					btn1.setText("µn¥X");
				}
				
			}
			//Log out
			else if(count==1) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				driver.navigate().to("https://www.youtube.com/logout");
				try {Thread.sleep(750);}catch(Exception ex) {ex.printStackTrace();}
				driver.navigate().to("https://accounts.google.com/ServiceLogin?uilel=3&hl=zh-TW&service=youtube&passive=true&continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Fhl%3Dzh-TW%26app%3Ddesktop%26next%3D%252F%26action_handle_signin%3Dtrue");				
				
				String theTextIWant =null;
				for(int i=0;i<=10;i++) {
					try {
						js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[1]/div/div[2]/div")));
						Thread.sleep(250);	
					}catch(Exception ex) {}
					
					try {
						js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div/div/form/content/section/div/content/div/div/ul/li[3]/div")));
						Thread.sleep(500);
						js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div/div/form/content/section/div/content/div/div[1]/ul/li[1]/div")));
						Thread.sleep(250);
						js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[4]/div/div[2]/div[3]/div[1]")));
						Thread.sleep(250);
						theTextIWant = js.executeScript("return arguments[0].innerHTML;",driver.findElement(By.xpath("//*[@id=\"headingText\"]/content"))).toString();
						Thread.sleep(250);
						if(theTextIWant!=null) {break;}
					}catch(Exception ex) {}
					
					
					
				}
				btn1.setText("µn¤J");
				count-=1;
			}
			
					
		}

	}
	
//Handle "btn2" events
	class btn2Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			tf1.setText("");
			pef.setText("");
		}
		
	}
	
	
	
	
	
}
