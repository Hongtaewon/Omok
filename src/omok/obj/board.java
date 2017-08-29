package omok.obj;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import omok.utils.rule;

@SuppressWarnings("serial")
public class board extends JFrame implements MouseListener, MouseMotionListener{

	//Object obj;
	map m; // 게임 시작 시 map객체를 생성합니다.
	stone[] t_stone = new stone[225]; // 오목판의 돌의 최대 갯수가 225이므로 225개를 할당합니다.
	rule r; // rule객체를 생성한 뒤 인자로 넘겨주기 위해 선언합니다.
	private int cnt= 1; // 검은돌을 하나 놓은 상태로 시작하기 때문에 돌의 갯수는 1부터 시작합니다.
	private int s=1; // 현재 돌의 색입니다. -1이면 검은 돌, 1이면 하얀 돌
	public board()
	{
		addMouseListener(this);
		addMouseMotionListener(this); //마우스 이벤트 적용
		setSize(900,900);
		setLocation(500, 50);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m = new map();
		r = new rule(m);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.orange);
		g.fillRect(75,75 , 750, 750);
		g.setColor(Color.black);
		for(int i =0;i<750;i+=50)
		{
			g.setColor(Color.black);
			g.drawLine(100+i, 100, 100+i, 800);
			g.drawLine(100, 100+i, 800, 100+i);
			g.fillOval(245, 245, 10, 10);
			g.fillOval(245, 645, 10, 10);
			g.fillOval(645, 245, 10, 10);
			g.fillOval(645, 645, 10, 10);
		}
		g.fillOval(430, 430, 40, 40);
		//g.setColor(Color.white);
		//g.fillOval(480, 430, 40, 40);
	}
	public void paintstone(Graphics g,int X,int Y,int c)
	{
		int x = 100+X*50;
		int y = 100+Y*50; // 넘어온 좌표를 계산하여 프레임창에 맞춰줍니다.
		//System.out.println("X좌표 : "+x+" Y좌표 : "+y+" Color : "+c);
		if(c == 1) // 돌이 하얀색일 경우
		{
			g.setColor(Color.white);
			g.fillOval(x-20, y-20, 40, 40);
		}
		else if(c == -1) // 돌이 검은색일 경우
		{
			g.setColor(Color.black);
			g.fillOval(x-20, y-20, 40, 40);
		}
		
		//m.PrintMap();
	}
	public void nextput(int x,int y)
	{
		if(x<=75 || y<=75 || x>=825 || y>=825)
		{
			//오목판을 넘어가서 마우스를 클릭하면 다시 클릭하도록 해줍니다.
			System.out.println("오목판을 벗어났습니다. 다시 정확한 위치를 지정해주세요.");
			return;
		}
		t_stone[cnt] =  new stone(x,y,s,r); // 생성자에 인자값을 넣어줍니다.
		if(t_stone[cnt].getP() == 1) // 돌이 오목판에 놓여져도 되는 상황일 때
		{
			int Px = t_stone[cnt].getX();
			int Py = t_stone[cnt].getY();
			int Sc = t_stone[cnt].getC(); // 넘어온 오목판의 값을 받아 저장합니다.
			paintstone(getGraphics(), Px, Py, Sc); // 돌을 오목판 위에 그려줍니다.
			cnt++; //돌의 갯수를 하나 증가시킵니다.
			if(s == 1) // 현재 돌의 색에 따라 다음 돌의 색을 정해줍니다.
			{
				s = -1;
			}
			else
			{
				s = 1;
			}
			if(r.winner(Px, Py, Sc)) // 현재 돌이 놓인 위치에서 승리할 수 있는지 확인합니다.
			{
				String win = Sc == 1?"백돌이 ":"흑돌이 ";
				System.out.println(win+"승리하였습니다.");
				JOptionPane.showMessageDialog(null, win+"승리하였습니다.");
				System.exit(0);
			}			
		}
		else if(t_stone[cnt].getP() == 2) // 현재 좌표의 돌을 놓게 되면 금수이기 때문에 놓을 수 없습니다.
		{
			System.out.println("금수를 두었습니다.");
		}
		else if(t_stone[cnt].getP() == 0) // 현재 좌표에 돌이 있기 때문에 놓을 수 없습니다.
		{
			System.out.println("돌이 이미 있습니다. 다른 곳에 두십시오.");
		}
		if(cnt>=225) // 오목판에 더 이상 놓을 수 없는 경우
		{
			System.out.println("더 이상 놓을 수 없습니다. 비겼습니다.");
			JOptionPane.showMessageDialog(null, "더 이상 놓을 수 없습니다. 비겼습니다.");
			System.exit(0);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		setTitle("x : "+e.getX()+ "y : "+e.getY());
		// x와 y좌표를 프레임 창 제목으로 띄워줍니다.
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		nextput(e.getX(),e.getY());
		// 프레임 안에서 마우스가 눌렸을 때를 감지하고 nextput으로 값을 넘겨줍니다.
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

