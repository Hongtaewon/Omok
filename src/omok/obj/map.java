package omok.obj;


public class map {
	
	private int[][] MapPoint = new int[15][15];
	//private int who;
	public map()
	{
		MapPoint[7][7] = -1;
	}
	public void setPoint(int i, int j,int c) {
		// TODO Auto-generated method stub
		MapPoint[j][i] = c;
	}
	public int getPoint(int i,int j)
	{
		//System.out.println(MapPoint[j][i]);
		return MapPoint[j][i];
	}
	/*public int getWho()
	{
		return who;
	}
	public void setwho(int c)
	{
		who = c;
	}*/

	public void PrintMap()
	{
		for(int i = 0;i<15;i++)
		{
			for(int j = 0;j<15;j++)
			{
				System.out.print(this.MapPoint[i][j]);
			}
			System.out.println();
		}
		System.out.println("---------------------");
	}

	
	/*private boolean check_open_close(int[] check)
	{
		if(check2three(check))
		{
			return false; //´ÝÈù»ïÀÇ °æ¿ì
		}
		return true; // ¿­¸°»ïÀÇ °æ¿ì
	}*/

	
	
}
/*
 * */

/*		
 * 		int flag_bsl = 0;
		int flag_hor = 0;
		int flag_sla = 0;
		int flag_ver = 0;
		//check_0
				if(y>=3)
				{
					cnt = 0;
					for(int j = y-3;j<=y-1;j++)
					{
						int cs = CheckStone(x, j, who);
						if(cs == 1) cnt++;
						else if(cs == 0) 
						{
							cnt = 0;
							break;
						}
					}
					//System.out.println("check_0 cnt is "+cnt);
					if(cnt==2) 
					{
						if(getPoint(x, y-4) == -who);
						else
						{
							check++;
							flag_ver = 1;
						}
					}
				}
				//check_1
				if(y>=3 && x<=11)
				{
					cnt = 0;
					int i = x+3;
					for(int j = y-3;j<=y-1;j++)
					{
						int cs = CheckStone(i, j, who);
						if(cs == 1) cnt++;
						else if(cs == 0) 
						{
							cnt = 0;
							break;
						}
						i--;
					}
					//System.out.println("check_1 cnt is "+cnt);
					if(cnt==2)
					{
						if(getPoint(x+4, y-4) == -who);
						else
						{
							check++;
							flag_sla = 1;
						}
					}
				}
				//check_2
				if(x<=11)
				{
					cnt = 0;
					for(int i = x+3;i >= x+1;i--)
					{
						int cs = CheckStone(i, y, who);
						if(cs == 1) cnt++;
						else if(cs == 0) 
						{
							cnt = 0;
							break;
						}
					}
					//System.out.println("check_2 cnt is "+cnt);
					if(cnt==2)
					{
						if(getPoint(x+4, y) == -who);
						else
						{
							check++;
							flag_hor = 1;
						}
					}
				}
				//check_3
				if(x<=11 && y<=11)
				{
					cnt = 0;
					int i = x+3;
					for(int j = y+3;j>=y+1;j--)
					{
						int cs = CheckStone(i, j, who);
						if(cs == 1) cnt++;
						else if(cs == 0) 
						{
							cnt = 0;
							break;
						}
						i--;
					}
					//System.out.println("check_3 cnt is "+cnt);
					if(cnt==2)
					{
						if(getPoint(x+4, y+4) == -who);
						else
						{
							check++;
							flag_bsl = 1;
						}
					}
				}
				//check_4
				if(flag_ver == 0)
				{
					if(y<=11)
					{
						cnt = 0;
						for(int j = y+3;j>=y+1;j--)
						{
							int cs = CheckStone(x, j, who);
							if(cs == 1) cnt++;
							else if(cs == 0) 
							{
								cnt = 0;
								break;
							}
						}
						//System.out.println("check_4 cnt is "+cnt);
						if(cnt==2)				
						{
							if(getPoint(x, y+4) == -who);
							else check++;
						}
					}
				}
				//check_5
				if(flag_sla == 0)
				{
					if(x>=3 && y<=11)
					{
						cnt = 0;
						int i = x-3;
						for(int j = y+3;j>=y+1;j--)
						{
							int cs = CheckStone(i, j, who);
							if(cs == 1) cnt++;
							else if(cs == 0) 
							{
								cnt = 0;
								break;
							}
							i++;
						}
						//System.out.println("check_5 cnt is "+cnt);
						if(cnt==2)				
						{
							if(getPoint(x-4, y+4) == -who);
							else check++;
						}
					}
				}
				//check_6
				if(flag_hor == 0)
				{
					if(x>=3)
					{
						cnt = 0;
						for(int i = x-3;i<=x-1;i++)
						{
							int cs = CheckStone(i, y, who);
							if(cs == 1) cnt++;
							else if(cs == 0) 
							{
								cnt = 0;
								break;
							}
						}
						//System.out.println("check_6 cnt is "+cnt);
						if(cnt==2) 
						{
							if(getPoint(x-4, y) == -who);
							else check++;
						}
					}
				}
				//check_7
				if(flag_bsl == 0)
				{
					if(x>=3 && y>=3)
					{
						cnt = 0;
						int i = x-3;
						for(int j = y-3;j<=y-1;j++)
						{
							int cs = CheckStone(i, j, who);
							if(cs == 1) cnt++;
							else if(cs == 0) 
							{
								cnt = 0;
								break;
							}
							i++;
						}
						//System.out.println("check_7 cnt is "+cnt);
						if(cnt==2)				
						{
							if(getPoint(x-4, y-4) == -who);
							else check++;
						}
					}
				}
				//System.out.println("check is "+check);
		*/
