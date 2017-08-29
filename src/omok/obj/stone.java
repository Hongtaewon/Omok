package omok.obj;

import omok.utils.rule;


public class stone {
	private int x;
	private int y;
	private int color; // ���� x,y��ǥ�� ���� �����ϴ� �����Դϴ�.
	private int possible = 0; // ���� ���� �� �ִ��� �������� �����ϴ� �����Դϴ�.
	rule r; // rule��ü�� �����ϱ� ���� ������ �����Դϴ�.
	public stone(int axisX,int axisY,int color,rule ruleobj)
	{
		int s = color;
		setX(axisX);
		setY(axisY);
		setC(s);
		r = ruleobj; // board���� �Ѿ�� ���ڷ� ���� �����մϴ�. 
		int c = r.checkmap(x,y,color); // rule�� checkmap�Լ��� �̿��Ͽ� ���� ���� �� �ִ��� �Ǵ��մϴ�.
		if(c == 1) // ���� ���� 1�� ����, 2�� �ݼ�, 0�� ���� ��ġ�� ����Դϴ�.
		{
			//state.s = getC();
			setP(1);
		}
		else if(c == 2)
		{
			setP(2);
		}
		else if(c == 0)
		{
			setP(0);
		}
		
	}
	private void setX(int axisX) // ������ ���� �����ϴ� �Լ��Դϴ�.
	{
		x = calcX(axisX);
	}
	private void setY(int axisY)
	{
		y = calcY(axisY);
	}
	private void setC(int C)
	{
		color = C;
	}
	private void setP(int P) 
	{
		possible = P;
	}
	public int getX()// ������ ���� �ٸ� Ŭ������ ��ü���� �ҷ����� ���� ���� �Լ��Դϴ�.
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getC()
	{
		return color;
	}
	public int getP() 
	{
		return possible;
	}
	private int calcX(int x) // �Ѿ�� ��ǥ���� 0~14 ������ �Ǽ��� ����մϴ�.
	{
		if((x-75) / 50 < 0) return 0;
		return (x-75) / 50;
	}	
	private int calcY(int y)
	{
		if((y-75) / 50 < 0) return 0;
		return (y-75) / 50;
	}
}
