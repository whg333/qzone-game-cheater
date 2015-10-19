package com.hoolai.qzone.sango.gui;

import static com.hoolai.qzone.Global.SANGO_APP_ID;
import static com.hoolai.util.gui.ImageFinder.findImageIcon;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.gargoylesoftware.htmlunit.ScriptException;
import com.hoolai.qzone.Global;
import com.hoolai.qzone.LoginHandler;
import com.hoolai.qzone.LoginUser;
import com.hoolai.qzone.PlatformInfo;
import com.hoolai.qzone.sango.SangoCheatRunner;
import com.hoolai.qzone.sango.SangoLoginHandler;
import com.hoolai.qzone.sango.SangoLoginInfo;
import com.hoolai.qzone.sango.vo.VoCollector;
import com.hoolai.util.gui.InfiniteProgressPanel;
import com.hoolai.util.gui.SliderImagePanel;
import com.hoolai.util.string.StringUtil;

@SuppressWarnings("serial")
public class SangoLoginFrame extends JFrame {

	private JMenuItem author;
	
	private SliderImagePanel sliderImagePanel;
	
	private JTextField qqInput;
	private JTextField pwdInput;

	private JButton loginButton;
	private JButton exitButton;

	private static final int WIDTH = 450;
	private static final int HEIGHT = 505;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SangoLoginFrame();
			}
		});
	}

	public SangoLoginFrame() {
		super("胡莱三国辅助器");
		Global.initLookAndFeel();
		initUI();
		initEventListener();

		setSize(WIDTH, HEIGHT);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private void initUI() {
		setLayout(null);	//设置布局器为null，目的是为了自己调整布局坐标

		initIconImage();
		initMenuBar();
		initLogoImages();
		initLoginPanel();
		
	}
	
	private void initIconImage() {
        setIconImage(findImageIcon("/image/guanYu.png").getImage());
	}

	private void initMenuBar(){
		JMenu about = new JMenu("关于"); 		//创建JMenu菜单对象
		author = new JMenuItem("作者");			//菜单项
		about.add(author); 						//将菜单项目添加到菜单
		JMenuBar jMenuBar = new JMenuBar(); 	//创建菜单工具栏
		jMenuBar.add(about); 					//将菜单增加到菜单工具栏
		setJMenuBar(jMenuBar); 					//为 窗体设置 菜单工具栏
	}
	
	private void initLogoImages(){
		List<ImageIcon> images = new ArrayList<ImageIcon>();
		images.add(findImageIcon("/image/1.jpg"));
		images.add(findImageIcon("/image/2.jpg"));
		images.add(findImageIcon("/image/3.jpg"));
		images.add(findImageIcon("/image/4.jpg"));
		sliderImagePanel = new SliderImagePanel(images);
		sliderImagePanel.setBounds(23, 20, 400, 300);
		add(sliderImagePanel);
		sliderImagePanel.start();
		
//		ImageLabel label = new ImageLabel(findImageIcon("/image/1.jpg"));
//	    label.setLocation(23, 20);
//	    add(label);
	}
	
	private void initLoginPanel(){
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(15, 335, 420, 110);
		loginPanel.setLayout(new GridLayout(3, 2, 3, 2));

		JLabel qqLabel = new JLabel("QQ账号：", JLabel.CENTER);
		qqInput = new JTextField();
		qqInput.requestFocus();
		loginPanel.add(qqLabel);
		loginPanel.add(qqInput);

		JLabel pwdLabel = new JLabel("QQ密码：", JLabel.CENTER);
		pwdInput = new JPasswordField();
		loginPanel.add(pwdLabel);
		loginPanel.add(pwdInput);

		loginButton = new JButton("登录");
		exitButton = new JButton("退出");
		loginPanel.add(loginButton);
		loginPanel.add(exitButton);
		
		Border border = new TitledBorder("胡莱三国辅助器登录");
		loginPanel.setBorder(border);
		add(loginPanel);
	}

	private void initEventListener() {
		author.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(SangoLoginFrame.this, "作者——【胡莱三国官方技术团队】"
						+System.getProperty("line.separator")+"联系——【wanghonggang@hoolai.com】");
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAndDoLogin();
			}
		});
		
		pwdInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					checkAndDoLogin();
				}
			}
		});

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAndExit();
			}
		});
	}
	
	private void checkAndExit(){
		int result = JOptionPane.showConfirmDialog(SangoLoginFrame.this, "是否确定退出胡莱三国辅助器？");
		if(result == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	
	private void checkAndDoLogin(){
		String qq = qqInput.getText();
		if (StringUtil.isEmpty(qq)) {
			JOptionPane.showMessageDialog(SangoLoginFrame.this, "QQ账号不能为空！请输入QQ账号！");
			qqInput.requestFocus();
			return;
		}
		String pwd = pwdInput.getText();
		if (StringUtil.isEmpty(pwd)) {
			JOptionPane.showMessageDialog(SangoLoginFrame.this, "QQ密码不能为空！请输入QQ密码!");
			pwdInput.requestFocus();
			return;
		}

		doLogin(qq, pwd);
	}
	
	private int tryLoginTimes;
	private InfiniteProgressPanel gl = new InfiniteProgressPanel();

	private void doLogin(String qq, String pwd) {
		final LoginUser loginUser = new LoginUser(qq, pwd);
		Global.execute(new Runnable() {
			@Override
			public void run() {
				setGlassPane(gl);
				gl.start();
				gl.setText("正在登陆QQ空间....");
				try {
					VoCollector vc = loginQzone(loginUser);
					gl.setText("登陆QQ空间成功！欢迎使用胡莱三国辅助器！");
					Thread.sleep(1000);
					Global.addSuccessLogin(loginUser);
					loginCheaterFrame(vc);
				} catch (ScriptException se) {
					gl.setText("登陆QQ空间失败！请检查QQ账号和QQ密码是否正确，并尝试重新登陆！");
					initNextTryLogin();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						//do nothing
					}
				} catch (InterruptedException ex) {
					//do nothing
				} finally {
					gl.stop();
				}
			}
		});
	}
	
	private void initNextTryLogin(){
		pwdInput.setText("");
		pwdInput.requestFocus();
		tryLoginTimes++;
		if(tryLoginTimes == 3){
			JOptionPane.showMessageDialog(SangoLoginFrame.this, "尝试登陆QQ空间失败3次，请重新输入QQ账号和QQ密码！");
			tryLoginTimes = 0;
			qqInput.setText("");
			qqInput.requestFocus();
		}
	}

	private VoCollector loginQzone(LoginUser loginUser) {
		try {
			LoginHandler loginHanlder = new LoginHandler(loginUser);
			PlatformInfo platformInfo = loginHanlder.loginAndGetPlatformInfo(SANGO_APP_ID);

			SangoLoginHandler loginSangoHandler = new SangoLoginHandler(platformInfo);
			SangoLoginInfo sangoLoginInfo = loginSangoHandler.requestSangoLoginInfo();

			SangoCheatRunner cheatRunner = new SangoCheatRunner(loginUser, platformInfo, sangoLoginInfo);
			cheatRunner.collectUserInfo();
			return cheatRunner.getVoCollector();
		} catch (ScriptException se) {
			//se.printStackTrace();
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void loginCheaterFrame(final VoCollector vc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//首先关闭当前登陆界面
				closeLoginFrame();
				
				//然后打开辅助器界面
				//TODO:这里的ArrayList用于后期的批量登陆功能记录批量登陆的QQ号码信息
				openCheaterFrame(vc);
			}
		});
	}
	
	private void closeLoginFrame(){
		sliderImagePanel.stop();
		dispose();
	}
	
	private void openCheaterFrame(final VoCollector vc){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SangoCheaterFrame(new ArrayList<VoCollector>(){{add(vc);}});
			}
		});
	}

}
