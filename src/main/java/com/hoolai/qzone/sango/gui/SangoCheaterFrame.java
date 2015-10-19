package com.hoolai.qzone.sango.gui;

import static com.hoolai.util.gui.ImageFinder.findImageIcon;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

import com.hoolai.qzone.Global;
import com.hoolai.qzone.LoginUser;
import com.hoolai.qzone.sango.vo.Barrack;
import com.hoolai.qzone.sango.vo.User;
import com.hoolai.qzone.sango.vo.VoCollector;
import com.hoolai.util.gui.ImageLabel;
import com.hoolai.util.string.StringUtil;

@SuppressWarnings("serial")
public class SangoCheaterFrame extends JFrame {

	private static final int WIDTH = 955;
	private static final int HEIGHT = 650;
	
	private final List<VoCollector> vcList;
	private LoginUser currentLoginUser;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new SangoCheaterFrame(Collections.EMPTY_LIST);
			}
		});
	}

	public SangoCheaterFrame(List<VoCollector> vcList) {
		super("胡莱三国辅助器");
		this.vcList = vcList;
		if(!vcList.isEmpty()){
			this.currentLoginUser = vcList.get(0).getLoginUser();
		}
		
		Global.initLookAndFeel();
		initUI();
		
		setSize(WIDTH, HEIGHT);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;  
        setLocation((screenWidth - WIDTH)/2, (screenHeight-HEIGHT)/2);  
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	@SuppressWarnings("unchecked")
	private void initUI(){
		setLayout(null);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(5, 10, 150, 610);
		JTable loginUserTable = new JTable(new LoginUserTableModel(hasVc() ? findLoginUserList() : Collections.EMPTY_LIST));
		loginUserTable.setRowHeight(20);
		TableColumnModel tcm = loginUserTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(20);
		tcm.getColumn(1).setPreferredWidth(100);
		leftPanel.setLayout(null);
		JScrollPane loginUserPanel = new JScrollPane(loginUserTable);
		loginUserPanel.setBounds(2, 5, 145, 395);
		leftPanel.add(loginUserPanel);
		add(leftPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(160, 10, 510, 610);
		
		centerPanel.setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(5, 5, 500, 280);
		initTabbedPanel(tabbedPane);
		centerPanel.add(tabbedPane);
		
		JPanel runPanel = new JPanel();
		runPanel.setBounds(5, 290, 500, 100);
		centerPanel.add(runPanel);
		
		JTextArea runResult = new JTextArea(3, 20);
		runResult.setEditable(false);
		JScrollPane runResultPane = new JScrollPane(runResult);
		runResultPane.setBounds(5, 400, 500, 200);
		centerPanel.add(runResultPane);
		add(centerPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(675, 10, 270, 610);
		rightPanel.setLayout(null);
		Border border = new TitledBorder("个人资料");
		
		JPanel userPanel = new JPanel();
		userPanel.setBounds(2, 5, 260, 200);
		userPanel.setLayout(new GridLayout(6, 6, 2, 5));
		User user = currentVc().getUser();
		Barrack barrack = currentVc().getBarrack();
		userPanel.add(new JLabel("名字："));
		userPanel.add(new JLabel(user.getName()));
		userPanel.add(new JLabel("连续登录："));
		userPanel.add(new JLabel(user.getActiveDays()+""));
		userPanel.add(new JLabel("国家："));
		userPanel.add(new JLabel(user.getCountryName()));
		userPanel.add(new JLabel("步兵："));
		userPanel.add(new JLabel(barrack.getFootmanNum()+""));
		userPanel.add(new JLabel("等级："));
		userPanel.add(new JLabel(user.getRank()+""));
		userPanel.add(new JLabel("骑兵："));
		userPanel.add(new JLabel(barrack.getRiderNum()+""));
		userPanel.add(new JLabel("经验："));
		userPanel.add(new JLabel(user.getFame()+""));
		userPanel.add(new JLabel("弓兵："));
		userPanel.add(new JLabel(barrack.getArcherNum()+""));
		userPanel.add(new JLabel("金币："));
		userPanel.add(new JLabel(user.getGold()+""));
		userPanel.add(new JLabel("特种兵："));
		userPanel.add(new JLabel(barrack.getSpecialNum()+""));
		userPanel.add(new JLabel("功勋："));
		userPanel.add(new JLabel(user.getHonor()+""));
		userPanel.add(new JLabel("紫晶钻："));
		userPanel.add(new JLabel(user.getZiJingZuan()+""));
		userPanel.setBorder(border);
		rightPanel.add(userPanel);
		add(rightPanel);
		
		ImageLabel label = new ImageLabel(findImageIcon("/image/ad.jpg"));
	    label.setLocation(-5, 270);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(isMouseLeftKey(e) || isMouseRightKey(e)){
					openWithIEAndDefaultBrowse("http://bbs.open.qq.com/forum.php?mod=forumdisplay&fid=220");
				}
			}
			private boolean isMouseLeftKey(MouseEvent e){
				return e.getButton() == MouseEvent.BUTTON1;
			}
			private boolean isMouseRightKey(MouseEvent e){
				return e.getButton() == MouseEvent.BUTTON3;
			}
		});
	    rightPanel.add(label);
		
		JMenu about = new JMenu("关于");
		JMenuItem author = new JMenuItem("作者");
		about.add(author);
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.add(about);
		author.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(SangoCheaterFrame.this, "作者——【胡莱三国官方技术团队】"
						+System.getProperty("line.separator")+"联系——【wanghonggang@hoolai.com】");
			}
		});
		
		JMenu openGame = new JMenu("打开游戏");
		JMenuItem openServer1 = new JMenuItem("【QQ空间】胡莱三国成就服");
		openGame.add(openServer1);
		jMenuBar.add(openGame);
		openServer1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String sangoServerUrl = currentVc().getSangoServerUrl();
				if(StringUtil.isEmpty(sangoServerUrl)){
					JOptionPane.showMessageDialog(SangoCheaterFrame.this, "没有可用的胡莱三国成就服URL地址");
					return;
				}
				openWithIEAndDefaultBrowse(sangoServerUrl);
			}
		});
		
		setJMenuBar(jMenuBar); // 为 窗体设置 菜单工具栏
	}
	
	private void openWithIEAndDefaultBrowse(String url){
		//启用cmd运行IE的方式来打开网址。
        String str = "cmd /c start iexplore \""+url+"\"";
        //System.out.println(str);
        try {
            Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //启用系统默认浏览器来打开网址。
        try {
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private List<LoginUser> findLoginUserList(){
		List<LoginUser> loginUserList = new ArrayList<LoginUser>();
		for(VoCollector vc:vcList){
			loginUserList.add(vc.getLoginUser());
		}
		return loginUserList;
	}
	
	@SuppressWarnings("unchecked")
	private void initTabbedPanel(JTabbedPane tabbedPane){
		JTable jTable = new JTable(new FriendTableModel(hasVc() ? currentVc().getFriendList() : Collections.EMPTY_LIST));
		jTable.setRowHeight(20);
		TableColumnModel tcm = jTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(20);
		tcm.getColumn(2).setPreferredWidth(80);
		JScrollPane scrollPanel = new JScrollPane(jTable);
		tabbedPane.add(scrollPanel);
		tabbedPane.setTitleAt(0, "好友列表");
		
		jTable = new JTable(new OfficerTableModel(hasVc() ? currentVc().getOfficerList() : Collections.EMPTY_LIST ));
//		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		jtable.setAutoCreateRowSorter(true);
//		jtable.setAutoscrolls(true);
//		jtable.setPreferredScrollableViewportSize(new Dimension(500, 280));
//		jtable.getTableHeader().setResizingAllowed(false);
//		jtable.setFillsViewportHeight(true);
//		fitTableColumns(jtable);
		jTable.setRowHeight(20);
		tcm = jTable.getColumnModel();
		tcm.getColumn(1).setPreferredWidth(100);
		scrollPanel = new JScrollPane(jTable);
		tabbedPane.add(scrollPanel);
		tabbedPane.setTitleAt(1, "将领列表");
		
		jTable = new JTable(new OwnCityTableModel(
				hasVc() ? currentVc().getOwnCityList() : Collections.EMPTY_LIST, 
				hasVc() ? currentVc().getOfficerMap() : Collections.EMPTY_MAP));
		jTable.setRowHeight(20);
		tcm = jTable.getColumnModel();
		tcm.getColumn(3).setPreferredWidth(300);
		scrollPanel = new JScrollPane(jTable);
		tabbedPane.add(scrollPanel);
		tabbedPane.setTitleAt(2, "城池列表");
		
		jTable = new JTable(new ItemBagTableModel(hasVc() ? currentVc().getItemBagList() : Collections.EMPTY_LIST));
		jTable.setRowHeight(20);
		tcm = jTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(30);
		tcm.getColumn(1).setPreferredWidth(70);
		scrollPanel = new JScrollPane(jTable);
		tabbedPane.add(scrollPanel);
		tabbedPane.setTitleAt(3, "道具列表");
	}
	
	private boolean hasVc(){
		return !vcList.isEmpty();
	}
	
	private VoCollector currentVc(){
		for(VoCollector vc:vcList){
			if(vc.getLoginUser().equals(currentLoginUser)){
				return vc;
			}
		}
		return new VoCollector(new LoginUser("null", "null"), "");
		//throw new IllegalArgumentException("没有找到当前登录用户的信息"+currentLoginUser);
	}
	
}
