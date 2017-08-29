package omok.utils;

import java.util.Arrays;

import omok.obj.map;


public class rule {
	
	map m; //map객체를 저장하기 위해 선언한 변수입니다.
	//private int who;
	public rule(map Mapobj)
	{
		m = Mapobj;
	}
	
	private int dir[][] = {
			{0,1},
			{-1,1},
			{-1,0},
			{-1,-1}
		}; // 돌을 추적할 떄 사용하는 방향 배열입니다.
	public boolean winner(int x,int y,int c) //돌이 5개가 되었는지 확인하는 함수입니다.
	{
		for(int j = 0;j<4;j++) // 방향 4개를 확인하기 위해 반복문을 돌립니다.
		{
			int cnt = 1;
			
			int temp_x = x;
			int temp_y = y;
			for(int i = 0;i<5;i++) // 최대 5개 좌표까지 확인하기 위해 반복문을 돌립니다.
			{
				temp_x = temp_x+dir[j][1];
				temp_y = temp_y+dir[j][0]; // 현재좌표에서 해당하는 방향으로 일정하게 좌표를 이동합니다.
				//System.out.println("현재 x좌표는 "+x + " y좌표는 "+y);
				//System.out.println("다음 x좌표는 "+temp_x + " y좌표는 "+temp_y + "체크할 색은 "+c);
				if (temp_x < 0 || temp_y < 0 || temp_x >= 15 || temp_y >= 15) // 맵밖으로 나가면 break를 걸어 다음으로 진행합니다.
					break;
				if(m.getPoint(temp_x, temp_y) == c) //돌이 이어지는 경우에만 cnt++을 하여 돌의 갯수를 저장합니다.
				{
					cnt++;
				}
				else
				{
					break;
				}
			}
			temp_x = x;
			temp_y = y;
			for(int i = 0;i<5;i++) 
			{
				temp_x = temp_x-dir[j][1];
				temp_y = temp_y-dir[j][0]; // 반대 방향으로 최대 5개를 확인합니다.
				
				if (temp_x < 0 || temp_y < 0 || temp_x >= 15 || temp_y >= 15) // 맵밖으로 나가면 break를 걸어 다음으로 진행합니다.
					break;
				if(m.getPoint(temp_x, temp_y) == c) //돌이 이어지는 경우에만 cnt++을 하여 돌의 갯수를 저장합니다.
				{
					cnt++;
				}
				else
				{
					break;
				}
			}
			//System.out.println("연속된 돌의 갯수는 : "+cnt);
			if(cnt >=5) // 돌의 갯수가 5개가 넘어가면 조건문을 확인합니다.
			{
				if(cnt >=6 && c == -1) //돌의 갯수가 6개가 넘고 돌이 검은 색일 경우 승리조건에 들지 못합니다.
					return false;
				else
					return true; //6이 안넘고 돌이 검은색이 아니면 승리!
			}
		}
		return false;
	}
	
	public int checkmap(int x,int y,int c)
	{
		//m.setwho(c);
		//who = c;
		if(Isthere(x,y)) // 놓을 좌표에 돌이 있는지 확인
		{
			if(checkrule(x,y,c)) // 놓을 좌표가 금수인지 아닌지 확인
			{
				m.setPoint(x, y, c); // 모두 통과하면 map객체에 좌표와 돌의 색을 전달
				return 1; // 좌표, 규정 모두 괜찮을 경우
			}
			return 2; // 좌표는 괜찮지만 규정에서 틀릴 경우
		}
		return 0; //좌표가 겹칠 경우
	}

	private boolean Isthere(int x,int y) // 놓으려는 곳에 돌이 있는지 없는지만 판단합니다.
	{
		if(m.getPoint(x, y) == 0)
		{
			return true; //아무것도 없을 경우
		}
		else
		{
			return false; //다른 돌이 있을 경우
		}
	}
	private boolean checkrule(int x,int y,int who)
	{
		//System.out.println("who is "+who);
		if(who == 1) return true; // 흰돌의 경우 금수를 검사하지 않습니다.
		//33인지 체크
		if(checkFoul(x,y,who)) // 검은돌만 금수인지 아닌지 확인합니다.
		{
			return false; // 금수일 경우
		}
		
		return true; //모든 조건을 만족했을 경우
	}
	private boolean checkFoul(int x,int y,int who)
	{
		int check = 0; // 줄이 열린삼일 경우를 저장하는 변수
		int cnt = 0; // 돌의 갯수를 저장하는 변수
		int[] ck; // 2차원 배열이지만 확인하는 것은 한 줄로 1차원이기 때문에 1차원 배열을 이용합니다.
		int k = 0; // 배열의 인덱스를 증가시키기 위해 필요한 변수
		
		//check0 vertical
		ck = new int[9]; // 돌이 놓인 자리에서 최대 +-4만큼 확인합니다.
		ck[4] = 1; // 놓을 돌의 위치는 중간에 위치시킵니다.
		k=0;
		cnt = 1; // 놓을 돌이 있다고 가정하고 하기 때문에 1로 시작합니다.
		for(int j = y-4;j<=y+4;j++)
		{
			if(k==4) 
			{
				k++;
				continue;
			}
			if(j<0 || j>14) // 맵의 범위 밖으로 넘어가는 경우 2를 넣어줍니다.
			{
				ck[k] = 2;
				k++;
				continue;
			}
			ck[k] = CheckStone(x,j,who); // 좌표의 오른쪽 줄 좌표를 확인합니다.
			if(ck[k] == 1) cnt++; // 같은 색 돌일 경우 cnt를 1 증가 시킵니다.
			k++;
		}
		if(cnt>=3) //체크하는 범위의 같은 돌이 3개 이상이 될 경우
		{
			if(!check2three(ck)) // 체크할 줄의 배열을 넘겨주어 열린삼인지 확인합니다.
			{
				check++; //열린삼일 경우 check를 1 증가 시킵니다.
			}
		}
		//System.out.println("vertical checking : "+check);
		//}
		//check1 diagonal
		ck = new int[9];
		ck[4] = 1;
		k=0;
		cnt = 1;
		int i = x+4;
		for(int j = y-4;j<=y+4;j++)
		{
			if(k==4) 
			{
				k++;
				i--;
				continue;
			}
			if(j<0 || j>14 || i<0 || i>14)
			{
				ck[k] = 2;
				k++;
				i--;
				continue;
			}
			ck[k] = CheckStone(i,j,who);
			if(ck[k] == 1) cnt++;
			k++;
			i--;
		}
		if(cnt>=3) //체크하는 범위의 같은 돌이 3개 이상이 될 경우
		{
			if(!check2three(ck))
			{
				check++;
			}
		}
		//System.out.println("diagonal checking : "+check);
		
		
		//check2 horizontal
		ck = new int[9];
		ck[4] = 1;
		k=0;
		cnt = 1;
		
		for(i = x+4;i>=x-4;i--)
		{
			if(k==4) 
			{
				k++;
				continue;
			}
			if(i<0 || i>14)
			{
				ck[k] = 2;
				k++;
				continue;
			}
			ck[k] = CheckStone(i,y,who);
			if(ck[k] == 1) cnt++;
			k++;
		}
		if(cnt>=3) //체크하는 범위의 같은 돌이 3개 이상이 될 경우
		{
			if(!check2three(ck))
			{
				check++;
			}
		}
		//System.out.println("horizontal checking : "+check);
		
		//check3 reverse diagonal
		ck = new int[9];
		ck[4] = 1;
		k=0;
		cnt = 1;
		i = x+4;
		for(int j = y+4;j>=y-4;j--)
		{
			if(k==4) 
			{
				k++;
				i--;
				continue;
			}
			if(j<0 || j>14 || i<0 || i>14)
			{
				ck[k] = 2;
				k++;
				i--;
				continue;
			}
			ck[k] = CheckStone(i,j,who);
			if(ck[k] == 1) cnt++;
			k++;
			i--;
		}
		if(cnt>=3) //체크하는 범위의 같은 돌이 3개 이상이 될 경우
		{
			if(!check2three(ck))
			{
				check++;
			}
		}
		//System.out.println("reverse diagonal checking : "+check);

		if(check>=2) return true; // 금수를 둔 경우
		return false; //금수가 아닌 경우
	}
	private int CheckStone(int x,int y,int c)
	{
		//System.out.println("y좌표 "+(100+y*50)+" x좌표 "+(100+x*50));
		if(m.getPoint(x, y) == c)
		{
			//System.out.println("same color");
			return 1; //같은 색의 돌일 경우
		}
		else if(m.getPoint(x, y) == -c)
		{
			//System.out.println("different color");
			return 0; //다른 색의 돌일 경우
		}
		//System.out.println("another");
		return -1;
	}
	
	private boolean check2three(int[] ck)
	{
		int cnt = 0;
		int temp_ck = 0;
		int last_state = -3;
		//int last_state2 = -2;
		int temp_ck_num = -1;
		int space_ck[] = new int[5];    // 밑의 알고리즘으로 검출하지 못하는 닫힌삼을 미리 판단하기 위한 배열
		int space_ck2[] = new int[6];   // 밑의 알고리즘으로 검출하지 못하는 닫힌삼을 미리 판단하기 위한 배열
		int[] snsns = {1,-1,1,-1,1};    //
		int[] snnsns = {1,-1,-1,1,-1,1};//
		int[] snsnns = {1,-1,1,-1,-1,1};//
		// -1 공백 0 다른돌 1 같은돌 2 막힘
		if(ck[3] == 0 || ck[3] == 2 || ck[5] == 0 || ck[5] == 2) return true; // 닫힘 놓을 자리의 바로 왼쪽이나 오른쪽에 다른 돌이 있거나 막혀있으면 닫힌 상태
		
		//snsns snnsns snsnns
		//이 패턴이 있을 경우 열린삼이 아니기 때문에 닫힘으로 간주한다.
		//현재 알고리즘에서 이 경우를 한정적으로 찾을 수 있기 때문에 미리 체크하여 처리해준다.
		for(int n = 0;n<5;n++)
		{
			System.arraycopy(ck, n, space_ck, 0, 5);
			if(Arrays.equals(space_ck, snsns))
				return true;
			if(n==4) break;
			System.arraycopy(ck, n, space_ck2, 0, 6);
			if(Arrays.equals(space_ck2, snnsns) || Arrays.equals(space_ck2, snsnns))
				return true;
		}
		for(int i = 0;i<5;i++) // 4를 기준으로 배열의 오른쪽부터 체크합니다.
		{
			//System.out.println("ck : "+Arrays.toString(ck));
			if(cnt>=4) return true; //돌의 갯수가 사 이상인 경우 무조건 가능한 경우
			if(ck[4+i] == 1) // 돌이 같은 색일 경우 cnt를 1 증가시킵니다.
			{
				cnt++;
				//last_state2 = last_state;
				last_state = 1;
			}
			else if(ck[4+i] == 0 || ck[4+i] == 2) // 돌이 다른 색이거나 막혀있을 경우
			{
				if(cnt==3) 
				{
					if(i == 7) // 무조건 닫히는 경우의 조건
						return true; //닫힘
					else if(i == 8) // 무조건 닫히는 경우의 조건
						if(ck[2] == 0 || ck[2] == 2)
							return true; // 닫힘
				}
				if(last_state == 1) // sx or sd 
				{
					return true; // 닫힘
				}
				//cnt>=4인 경우 어차피 가능한 수
				//다른 돌이던가 막혀있으면 다음 작업이 무의미하기 때문에 break를 건다
				break;
			}
			else if(ck[4+i] == -1) // 공백의 경우
			{
				if(last_state == 1) // sn
				{
					if(4+i+1<9)
					{
						if(ck[4+i+1] == 0 || ck[4+i+1] == 2) // snx or snd인 경우 왼쪽이 막히면 닫힌 삼일 수 도 있습니다.
						{
							temp_ck++; // 한쪽이 어설프게 막혔다는 것을 알림
							temp_ck_num = 4+i;
							break; // 막힌 뒤로 찾아봐야 불필요한 작업
						}
					}
				}
				else if(last_state == -1) //?nn
				{
					break; //공백이 연속으로 2개 이상이기 때문에 절대 닫히지 않습니다.
						   //따라서 왼쪽으로 가며 닫힌 경우를 찾는다.
				}
				//last_state2 = last_state;
				last_state=-1;
			}
		}
		last_state = -3;
		//last_state2 = -2;
		for(int i = 1;i<5;i++)
		{
			if(cnt>=4) return true; //사 이상인 경우 무조건 가능한 경우
			
			if(ck[4-i] == 1) //같은 돌인 경우
			{
				cnt++;
				//last_state2 = last_state;
				last_state = 1;
			}
			else if(ck[4-i] == 0 || ck[4-i] == 2) //막힌 경우
			{
				//System.out.println("last_state : "+last_state);
				if(last_state == 1) // xss의 경우이기 때문에 닫힌다.
				{
					return true; // 닫힘
				}
				if(temp_ck == 1 && 4-i == 2) return true; // 닫힐 가능성이 있고 ck[2]가 막힌 경우 무조건 닫힌 경우
				
				if(cnt<=2) //8.22 작업 여기까지 여기부터 작업 시작
				{
					return true; // cnt가 2이하에 현재위치가 닫히면 열린삼의 가능성이 없습니다.
				}
				else if(cnt==3 && temp_ck == 0)
				{
					return false; //cnt가 3이고 ck[3]에서 막힌경우는 없기 때문에 무조건 열린삼입니다.
				}

				//cnt>=4인 경우 어차피 가능한 수
				//다른 돌이던가 막혀있으면 다음 작업이 무의미하기 때문에 break를 해줍니다.
				break;
			}
			else if(ck[4-i] == -1) //공백
			{
				//System.out.println("temp_ck_num : "+temp_ck_num+"ck : "+Arrays.toString(ck));
				if(last_state == 1) // ns
				{
					if(4-i-1>-1)
					{
						if(ck[4-i-1] == 0 || ck[4-i-1] == 2) // xns or dns인 경우 오른쪽이 막히면 닫힌 삼일 수 도 있습니다.
						{
							if(temp_ck==1)
							{
								//System.out.println("ok? i value is "+i);
								for(int sp_ck=i+1;sp_ck<temp_ck_num;sp_ck++)
								{
									if(ck[sp_ck]==-1) return false;
									//양쪽에 공백 2개 이외에 하나의 공백이 더 있을 경우 금수입니다.
								}
								return true; // 양쪽이 어설프게 막혔다는 것을 알림. 즉 닫힌 상태
							}
							break; // 막힌 뒤로 찾아봐야 불필요한 작업
						}
					}
				}
				else if(last_state == -1) // nn?
				{
					if(cnt<=2) return true;
					break; //공백이 연속으로 2개 이상이기 때문에 절대 닫히지 않습니다.
						   //따라서 왼쪽으로 가며 닫힌 경우를 찾습니다.
				}
				//last_state2 = last_state;
				last_state=-1;
			}
		}

		return false; //모든 알고리즘을 통과하면 남은건 열린삼입니다.
	}
	
}
