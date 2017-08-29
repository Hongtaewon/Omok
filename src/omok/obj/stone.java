package omok.obj;

import omok.utils.rule;


public class stone {
	private int x;
	private int y;
	private int color; // 돌의 x,y좌표와 색을 저장하는 변수입니다.
	private int possible = 0; // 돌이 놓을 수 있는지 없는지를 저장하는 변수입니다.
	rule r; // rule객체를 저장하기 위해 선언한 변수입니다.
	public stone(int axisX,int axisY,int color,rule ruleobj)
	{
		int s = color;
		setX(axisX);
		setY(axisY);
		setC(s);
		r = ruleobj; // board에서 넘어온 인자로 값을 세팅합니다. 
		int c = r.checkmap(x,y,color); // rule의 checkmap함수를 이용하여 돌을 놓을 수 있는지 판단합니다.
		if(c == 1) // 값에 따라 1은 가능, 2는 금수, 0은 돌이 겹치는 경우입니다.
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
	private void setX(int axisX) // 변수의 값을 세팅하는 함수입니다.
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
	public int getX()// 변수의 값을 다른 클래스나 객체에서 불러오기 위해 만든 함수입니다.
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
	private int calcX(int x) // 넘어온 좌표값을 0~14 사이의 실수로 계산합니다.
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
