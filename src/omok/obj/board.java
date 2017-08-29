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
	map m; // ���� ���� �� map��ü�� �����մϴ�.
	stone[] t_stone = new stone[225]; // �������� ���� �ִ� ������ 225�̹Ƿ� 225���� �Ҵ��մϴ�.
	rule r; // rule��ü�� ������ �� ���ڷ� �Ѱ��ֱ� ���� �����մϴ�.
	private int cnt= 1; // �������� �ϳ� ���� ���·� �����ϱ� ������ ���� ������ 1���� �����մϴ�.
	private int s=1; // ���� ���� ���Դϴ�. -1�̸� ���� ��, 1�̸� �Ͼ� ��
	public board()
	{
		addMouseListener(this);
		addMouseMotionListener(this); //���콺 �̺�Ʈ ����
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
		int y = 100+Y*50; // �Ѿ�� ��ǥ�� ����Ͽ� ������â�� �����ݴϴ�.
		//System.out.println("X��ǥ : "+x+" Y��ǥ : "+y+" Color : "+c);
		if(c == 1) // ���� �Ͼ���� ���
		{
			g.setColor(Color.white);
			g.fillOval(x-20, y-20, 40, 40);
		}
		else if(c == -1) // ���� �������� ���
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
			//�������� �Ѿ�� ���콺�� Ŭ���ϸ� �ٽ� Ŭ���ϵ��� ���ݴϴ�.
			System.out.println("�������� ������ϴ�. �ٽ� ��Ȯ�� ��ġ�� �������ּ���.");
			return;
		}
		t_stone[cnt] =  new stone(x,y,s,r); // �����ڿ� ���ڰ��� �־��ݴϴ�.
		if(t_stone[cnt].getP() == 1) // ���� �����ǿ� �������� �Ǵ� ��Ȳ�� ��
		{
			int Px = t_stone[cnt].getX();
			int Py = t_stone[cnt].getY();
			int Sc = t_stone[cnt].getC(); // �Ѿ�� �������� ���� �޾� �����մϴ�.
			paintstone(getGraphics(), Px, Py, Sc); // ���� ������ ���� �׷��ݴϴ�.
			cnt++; //���� ������ �ϳ� ������ŵ�ϴ�.
			if(s == 1) // ���� ���� ���� ���� ���� ���� ���� �����ݴϴ�.
			{
				s = -1;
			}
			else
			{
				s = 1;
			}
			if(r.winner(Px, Py, Sc)) // ���� ���� ���� ��ġ���� �¸��� �� �ִ��� Ȯ���մϴ�.
			{
				String win = Sc == 1?"�鵹�� ":"�浹�� ";
				System.out.println(win+"�¸��Ͽ����ϴ�.");
				JOptionPane.showMessageDialog(null, win+"�¸��Ͽ����ϴ�.");
				System.exit(0);
			}			
		}
		else if(t_stone[cnt].getP() == 2) // ���� ��ǥ�� ���� ���� �Ǹ� �ݼ��̱� ������ ���� �� �����ϴ�.
		{
			System.out.println("�ݼ��� �ξ����ϴ�.");
		}
		else if(t_stone[cnt].getP() == 0) // ���� ��ǥ�� ���� �ֱ� ������ ���� �� �����ϴ�.
		{
			System.out.println("���� �̹� �ֽ��ϴ�. �ٸ� ���� �νʽÿ�.");
		}
		if(cnt>=225) // �����ǿ� �� �̻� ���� �� ���� ���
		{
			System.out.println("�� �̻� ���� �� �����ϴ�. �����ϴ�.");
			JOptionPane.showMessageDialog(null, "�� �̻� ���� �� �����ϴ�. �����ϴ�.");
			System.exit(0);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		setTitle("x : "+e.getX()+ "y : "+e.getY());
		// x�� y��ǥ�� ������ â �������� ����ݴϴ�.
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		nextput(e.getX(),e.getY());
		// ������ �ȿ��� ���콺�� ������ ���� �����ϰ� nextput���� ���� �Ѱ��ݴϴ�.
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

