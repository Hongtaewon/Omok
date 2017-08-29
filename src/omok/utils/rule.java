package omok.utils;

import java.util.Arrays;

import omok.obj.map;


public class rule {
	
	map m; //map��ü�� �����ϱ� ���� ������ �����Դϴ�.
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
		}; // ���� ������ �� ����ϴ� ���� �迭�Դϴ�.
	public boolean winner(int x,int y,int c) //���� 5���� �Ǿ����� Ȯ���ϴ� �Լ��Դϴ�.
	{
		for(int j = 0;j<4;j++) // ���� 4���� Ȯ���ϱ� ���� �ݺ����� �����ϴ�.
		{
			int cnt = 1;
			
			int temp_x = x;
			int temp_y = y;
			for(int i = 0;i<5;i++) // �ִ� 5�� ��ǥ���� Ȯ���ϱ� ���� �ݺ����� �����ϴ�.
			{
				temp_x = temp_x+dir[j][1];
				temp_y = temp_y+dir[j][0]; // ������ǥ���� �ش��ϴ� �������� �����ϰ� ��ǥ�� �̵��մϴ�.
				//System.out.println("���� x��ǥ�� "+x + " y��ǥ�� "+y);
				//System.out.println("���� x��ǥ�� "+temp_x + " y��ǥ�� "+temp_y + "üũ�� ���� "+c);
				if (temp_x < 0 || temp_y < 0 || temp_x >= 15 || temp_y >= 15) // �ʹ����� ������ break�� �ɾ� �������� �����մϴ�.
					break;
				if(m.getPoint(temp_x, temp_y) == c) //���� �̾����� ��쿡�� cnt++�� �Ͽ� ���� ������ �����մϴ�.
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
				temp_y = temp_y-dir[j][0]; // �ݴ� �������� �ִ� 5���� Ȯ���մϴ�.
				
				if (temp_x < 0 || temp_y < 0 || temp_x >= 15 || temp_y >= 15) // �ʹ����� ������ break�� �ɾ� �������� �����մϴ�.
					break;
				if(m.getPoint(temp_x, temp_y) == c) //���� �̾����� ��쿡�� cnt++�� �Ͽ� ���� ������ �����մϴ�.
				{
					cnt++;
				}
				else
				{
					break;
				}
			}
			//System.out.println("���ӵ� ���� ������ : "+cnt);
			if(cnt >=5) // ���� ������ 5���� �Ѿ�� ���ǹ��� Ȯ���մϴ�.
			{
				if(cnt >=6 && c == -1) //���� ������ 6���� �Ѱ� ���� ���� ���� ��� �¸����ǿ� ���� ���մϴ�.
					return false;
				else
					return true; //6�� �ȳѰ� ���� �������� �ƴϸ� �¸�!
			}
		}
		return false;
	}
	
	public int checkmap(int x,int y,int c)
	{
		//m.setwho(c);
		//who = c;
		if(Isthere(x,y)) // ���� ��ǥ�� ���� �ִ��� Ȯ��
		{
			if(checkrule(x,y,c)) // ���� ��ǥ�� �ݼ����� �ƴ��� Ȯ��
			{
				m.setPoint(x, y, c); // ��� ����ϸ� map��ü�� ��ǥ�� ���� ���� ����
				return 1; // ��ǥ, ���� ��� ������ ���
			}
			return 2; // ��ǥ�� �������� �������� Ʋ�� ���
		}
		return 0; //��ǥ�� ��ĥ ���
	}

	private boolean Isthere(int x,int y) // �������� ���� ���� �ִ��� �������� �Ǵ��մϴ�.
	{
		if(m.getPoint(x, y) == 0)
		{
			return true; //�ƹ��͵� ���� ���
		}
		else
		{
			return false; //�ٸ� ���� ���� ���
		}
	}
	private boolean checkrule(int x,int y,int who)
	{
		//System.out.println("who is "+who);
		if(who == 1) return true; // ���� ��� �ݼ��� �˻����� �ʽ��ϴ�.
		//33���� üũ
		if(checkFoul(x,y,who)) // �������� �ݼ����� �ƴ��� Ȯ���մϴ�.
		{
			return false; // �ݼ��� ���
		}
		
		return true; //��� ������ �������� ���
	}
	private boolean checkFoul(int x,int y,int who)
	{
		int check = 0; // ���� �������� ��츦 �����ϴ� ����
		int cnt = 0; // ���� ������ �����ϴ� ����
		int[] ck; // 2���� �迭������ Ȯ���ϴ� ���� �� �ٷ� 1�����̱� ������ 1���� �迭�� �̿��մϴ�.
		int k = 0; // �迭�� �ε����� ������Ű�� ���� �ʿ��� ����
		
		//check0 vertical
		ck = new int[9]; // ���� ���� �ڸ����� �ִ� +-4��ŭ Ȯ���մϴ�.
		ck[4] = 1; // ���� ���� ��ġ�� �߰��� ��ġ��ŵ�ϴ�.
		k=0;
		cnt = 1; // ���� ���� �ִٰ� �����ϰ� �ϱ� ������ 1�� �����մϴ�.
		for(int j = y-4;j<=y+4;j++)
		{
			if(k==4) 
			{
				k++;
				continue;
			}
			if(j<0 || j>14) // ���� ���� ������ �Ѿ�� ��� 2�� �־��ݴϴ�.
			{
				ck[k] = 2;
				k++;
				continue;
			}
			ck[k] = CheckStone(x,j,who); // ��ǥ�� ������ �� ��ǥ�� Ȯ���մϴ�.
			if(ck[k] == 1) cnt++; // ���� �� ���� ��� cnt�� 1 ���� ��ŵ�ϴ�.
			k++;
		}
		if(cnt>=3) //üũ�ϴ� ������ ���� ���� 3�� �̻��� �� ���
		{
			if(!check2three(ck)) // üũ�� ���� �迭�� �Ѱ��־� ���������� Ȯ���մϴ�.
			{
				check++; //�������� ��� check�� 1 ���� ��ŵ�ϴ�.
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
		if(cnt>=3) //üũ�ϴ� ������ ���� ���� 3�� �̻��� �� ���
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
		if(cnt>=3) //üũ�ϴ� ������ ���� ���� 3�� �̻��� �� ���
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
		if(cnt>=3) //üũ�ϴ� ������ ���� ���� 3�� �̻��� �� ���
		{
			if(!check2three(ck))
			{
				check++;
			}
		}
		//System.out.println("reverse diagonal checking : "+check);

		if(check>=2) return true; // �ݼ��� �� ���
		return false; //�ݼ��� �ƴ� ���
	}
	private int CheckStone(int x,int y,int c)
	{
		//System.out.println("y��ǥ "+(100+y*50)+" x��ǥ "+(100+x*50));
		if(m.getPoint(x, y) == c)
		{
			//System.out.println("same color");
			return 1; //���� ���� ���� ���
		}
		else if(m.getPoint(x, y) == -c)
		{
			//System.out.println("different color");
			return 0; //�ٸ� ���� ���� ���
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
		int space_ck[] = new int[5];    // ���� �˰������� �������� ���ϴ� �������� �̸� �Ǵ��ϱ� ���� �迭
		int space_ck2[] = new int[6];   // ���� �˰������� �������� ���ϴ� �������� �̸� �Ǵ��ϱ� ���� �迭
		int[] snsns = {1,-1,1,-1,1};    //
		int[] snnsns = {1,-1,-1,1,-1,1};//
		int[] snsnns = {1,-1,1,-1,-1,1};//
		// -1 ���� 0 �ٸ��� 1 ������ 2 ����
		if(ck[3] == 0 || ck[3] == 2 || ck[5] == 0 || ck[5] == 2) return true; // ���� ���� �ڸ��� �ٷ� �����̳� �����ʿ� �ٸ� ���� �ְų� ���������� ���� ����
		
		//snsns snnsns snsnns
		//�� ������ ���� ��� �������� �ƴϱ� ������ �������� �����Ѵ�.
		//���� �˰��򿡼� �� ��츦 ���������� ã�� �� �ֱ� ������ �̸� üũ�Ͽ� ó�����ش�.
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
		for(int i = 0;i<5;i++) // 4�� �������� �迭�� �����ʺ��� üũ�մϴ�.
		{
			//System.out.println("ck : "+Arrays.toString(ck));
			if(cnt>=4) return true; //���� ������ �� �̻��� ��� ������ ������ ���
			if(ck[4+i] == 1) // ���� ���� ���� ��� cnt�� 1 ������ŵ�ϴ�.
			{
				cnt++;
				//last_state2 = last_state;
				last_state = 1;
			}
			else if(ck[4+i] == 0 || ck[4+i] == 2) // ���� �ٸ� ���̰ų� �������� ���
			{
				if(cnt==3) 
				{
					if(i == 7) // ������ ������ ����� ����
						return true; //����
					else if(i == 8) // ������ ������ ����� ����
						if(ck[2] == 0 || ck[2] == 2)
							return true; // ����
				}
				if(last_state == 1) // sx or sd 
				{
					return true; // ����
				}
				//cnt>=4�� ��� ������ ������ ��
				//�ٸ� ���̴��� ���������� ���� �۾��� ���ǹ��ϱ� ������ break�� �Ǵ�
				break;
			}
			else if(ck[4+i] == -1) // ������ ���
			{
				if(last_state == 1) // sn
				{
					if(4+i+1<9)
					{
						if(ck[4+i+1] == 0 || ck[4+i+1] == 2) // snx or snd�� ��� ������ ������ ���� ���� �� �� �ֽ��ϴ�.
						{
							temp_ck++; // ������ ����� �����ٴ� ���� �˸�
							temp_ck_num = 4+i;
							break; // ���� �ڷ� ã�ƺ��� ���ʿ��� �۾�
						}
					}
				}
				else if(last_state == -1) //?nn
				{
					break; //������ �������� 2�� �̻��̱� ������ ���� ������ �ʽ��ϴ�.
						   //���� �������� ���� ���� ��츦 ã�´�.
				}
				//last_state2 = last_state;
				last_state=-1;
			}
		}
		last_state = -3;
		//last_state2 = -2;
		for(int i = 1;i<5;i++)
		{
			if(cnt>=4) return true; //�� �̻��� ��� ������ ������ ���
			
			if(ck[4-i] == 1) //���� ���� ���
			{
				cnt++;
				//last_state2 = last_state;
				last_state = 1;
			}
			else if(ck[4-i] == 0 || ck[4-i] == 2) //���� ���
			{
				//System.out.println("last_state : "+last_state);
				if(last_state == 1) // xss�� ����̱� ������ ������.
				{
					return true; // ����
				}
				if(temp_ck == 1 && 4-i == 2) return true; // ���� ���ɼ��� �ְ� ck[2]�� ���� ��� ������ ���� ���
				
				if(cnt<=2) //8.22 �۾� ������� ������� �۾� ����
				{
					return true; // cnt�� 2���Ͽ� ������ġ�� ������ �������� ���ɼ��� �����ϴ�.
				}
				else if(cnt==3 && temp_ck == 0)
				{
					return false; //cnt�� 3�̰� ck[3]���� �������� ���� ������ ������ �������Դϴ�.
				}

				//cnt>=4�� ��� ������ ������ ��
				//�ٸ� ���̴��� ���������� ���� �۾��� ���ǹ��ϱ� ������ break�� ���ݴϴ�.
				break;
			}
			else if(ck[4-i] == -1) //����
			{
				//System.out.println("temp_ck_num : "+temp_ck_num+"ck : "+Arrays.toString(ck));
				if(last_state == 1) // ns
				{
					if(4-i-1>-1)
					{
						if(ck[4-i-1] == 0 || ck[4-i-1] == 2) // xns or dns�� ��� �������� ������ ���� ���� �� �� �ֽ��ϴ�.
						{
							if(temp_ck==1)
							{
								//System.out.println("ok? i value is "+i);
								for(int sp_ck=i+1;sp_ck<temp_ck_num;sp_ck++)
								{
									if(ck[sp_ck]==-1) return false;
									//���ʿ� ���� 2�� �̿ܿ� �ϳ��� ������ �� ���� ��� �ݼ��Դϴ�.
								}
								return true; // ������ ����� �����ٴ� ���� �˸�. �� ���� ����
							}
							break; // ���� �ڷ� ã�ƺ��� ���ʿ��� �۾�
						}
					}
				}
				else if(last_state == -1) // nn?
				{
					if(cnt<=2) return true;
					break; //������ �������� 2�� �̻��̱� ������ ���� ������ �ʽ��ϴ�.
						   //���� �������� ���� ���� ��츦 ã���ϴ�.
				}
				//last_state2 = last_state;
				last_state=-1;
			}
		}

		return false; //��� �˰����� ����ϸ� ������ �������Դϴ�.
	}
	
}
