package utilToSQLserver;

import java.sql.*;
import java.util.ArrayList;

public class ToSQLserver {
    public static Connection dbConn = null;
    public PreparedStatement pstt = null;
    public Statement stt = null;
    
    public ResultSet result = null;
    public static String Username;
    
    public ArrayList<String> flowerInfo = new ArrayList<>();
    public int success = 0;
    public int Num = 0;

    String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=MyDB";
    String name = "sa";
    String passwd = "root";
    String toSelectFlower = "select * from flower where name=";
    String toSelectUser = "select * from �û���Ϣ�� where name=";
    String toSelectDD = "select * from Orderinformation where Ono=";
	
   
    public ToSQLserver() {
        try {
            //1.��������
            //Class.forName����������,���ǳ�ʼ����������.�����Ǹ�����MySQL��Driver����,
            // ���ھ�̬�������ͨ��JDBC��DriverManagerע����һ������.����Ҳ����ֱ��ʹ��JDBC������������ע��mysql����.
            // �Ӷ�����ʹ��Class.forName.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.����
            dbConn = DriverManager.getConnection(dbURL, name, passwd);
            System.out.println("�������ݿ�ɹ�");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //��黨�ܿ��                          δʹ��
    public ArrayList<String> selectFlowerNummethod(ArrayList list) {
    	
        String sql = "select num from Flower where name = '"+list.get(0)+"'";
        System.out.println(sql);

        try {
            pstt = dbConn.prepareStatement(sql);
            result = pstt.executeQuery();
            success = 1;
            Num = Integer.parseInt(result.getString("num"));
            System.out.println(Num);
            System.out.println("��ѯ�ɹ�");
    		
    		while (result.next()) {
    			flowerInfo.add(result.getString("Ono"));
    			flowerInfo.add(result.getString("FlowerName"));
    			flowerInfo.add(result.getString("Onum"));
    			flowerInfo.add(result.getString("price"));
    			flowerInfo.add(result.getString("Zongjia"));
    		}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
        return flowerInfo;
    }
    

    //���һ���
    public ArrayList<String> selectFlowermethod(String text1) {
        String uninSelect = toSelectFlower + "'" + text1 + "'";
        System.out.println(uninSelect);
        try {
            pstt = dbConn.prepareStatement(uninSelect);
            result = pstt.executeQuery();
            System.out.println("��ѯ�ɹ�");

            while (result.next()) {
                flowerInfo.add(result.getString("name"));
                flowerInfo.add(result.getString("type"));
                flowerInfo.add(result.getString("num"));
                flowerInfo.add(result.getString("price"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowerInfo;
    }
    
    
    
    //���Ҷ���
    public ArrayList<String> selectDDmethod(String text1) {
    	String uninSelect = toSelectDD + "'" + text1 + "'";
    	System.out.println(uninSelect);
    	try {
    		pstt = dbConn.prepareStatement(uninSelect);
    		result = pstt.executeQuery();
    		System.out.println("��ѯ�ɹ�");
    		
    		while (result.next()) {
    			flowerInfo.add(result.getString("Ono"));
    			flowerInfo.add(result.getString("FlowerName"));
    			flowerInfo.add(result.getString("Onum"));
    			flowerInfo.add(result.getString("price"));
    			flowerInfo.add(result.getString("Zongjia"));
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return flowerInfo;
    }
    
    //�����û�����
    public ArrayList<String> selectUserDDmethod(String text1) {
    	//String uninSelect = toSelectDD + "'" + text1 + "'";
    	String sql = "select * from Orderinformation where FlowerName ='"+text1+"'";
    	System.out.println(sql);
    	try {
    		pstt = dbConn.prepareStatement(sql);
    		result = pstt.executeQuery();
    		System.out.println("��ѯ�ɹ�");
    		
    		while (result.next()) {
    			flowerInfo.add(result.getString("Ono"));
    			flowerInfo.add(result.getString("FlowerName"));
    			flowerInfo.add(result.getString("Onum"));
    			flowerInfo.add(result.getString("price"));
    			flowerInfo.add(result.getString("Zongjia"));
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return flowerInfo;
    }

    
    
    //�����û�
    public ArrayList<String> selectUsermethod(String text1) {
    	String uninSelect = toSelectUser + "'" + text1 + "'";
    	System.out.println(uninSelect);
    	try {
    		pstt = dbConn.prepareStatement(uninSelect);
    		result = pstt.executeQuery();
    		System.out.println("��ѯ�ɹ�");
    		
    		while (result.next()) {
    			flowerInfo.add(result.getString("name"));
    			flowerInfo.add(result.getString("password"));
    			flowerInfo.add(result.getString("address"));
    			flowerInfo.add(result.getString("phone"));
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return flowerInfo;
    }
    
    
    
    
    //�û���¼��ѯ
    public boolean loginmethod(ArrayList list) {
    	//String sql = "select * from Admin where name = '"+name+"'and password = '"+password+"'";//'"+name+"'
    	//String toInsert = "insert into �û���Ϣ�� (name,password,address,phone) values";
    	//String toInsert = "select * from �û���Ϣ�� where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'";
        //toInsert = toInsert + "('" + list.get(0) + "'and password = '" + list.get(1) + "')";
    	boolean res = false;
    	try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
			System.out.println("�������ݿ�ɹ���");
			Statement stt = conn.createStatement();
			String sql = "select * from �û���Ϣ�� where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'
			ResultSet rs = stt.executeQuery(sql);
			
			if(rs.next())
				res = true;
				

			System.out.println(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return res; 
    }
    
    
    //����Ա�û���¼��ѯ
    public boolean Adminloginmethod(ArrayList list) {
    	//String sql = "select * from Admin where name = '"+name+"'and password = '"+password+"'";//'"+name+"'
    	//String toInsert = "insert into �û���Ϣ�� (name,password,address,phone) values";
    	//String toInsert = "select * from Admin where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'";
    	//toInsert = toInsert + "('" + list.get(0) + "'and password = '" + list.get(1) + "')";
    	boolean res = false;
    	try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
			System.out.println("�������ݿ�ɹ���");
			Statement stt = conn.createStatement();
			String sql = "select * from Admin where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'";
			ResultSet rs = stt.executeQuery(sql);
			
			if(rs.next())
				res = true;
				

			System.out.println(sql);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return res; 
    }
    
    
    
    //�����û����ݷ���
    public void insertUsermethod(ArrayList list) {
        //insert flowerInfo(Bno,Bname,Bauthor,Bclass)values('0003','���μ�','��ж�','����')
    	//String sql = "insert into �û���Ϣ�� (name,password,address,phone) values('"+name+"','"+type+"','"+count+"','"+price+"')";
        //String toInsert = "insert flowerInfo(Bno,Bname,Bauthor,Bclass)values";
    	String toInsert = "insert into �û���Ϣ�� (name,password,address,phone) values";
        toInsert = toInsert + "('" + list.get(0) + "','" + list.get(1) + "','" + list.get(2)
                + "','" + list.get(3) + "')";
        try {
            pstt = dbConn.prepareStatement(toInsert);
            success = pstt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }
    
    //���뻨�����ݷ���
    public void insertFlowermethod(ArrayList list) {
    	//insert flowerInfo(Bno,Bname,Bauthor,Bclass)values('0003','���μ�','��ж�','����')
    	//String sql = "insert into �û���Ϣ�� (name,password,address,phone) values('"+name+"','"+type+"','"+count+"','"+price+"')";
    	//String toInsert = "insert flowerInfo(Bno,Bname,Bauthor,Bclass)values";
    	String toInsert = "insert into flower (name,type,num,price) values";
    	toInsert = toInsert + "('" + list.get(0) + "','" + list.get(1) + "','" + list.get(2)
    	+ "','" + list.get(3) + "')";
    	try {
    		pstt = dbConn.prepareStatement(toInsert);
    		success = pstt.executeUpdate();
    		
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }
    
    //���붩�����ݷ���
    public void insertOrdermethod(ArrayList list) {
    	//insert flowerInfo(Bno,Bname,Bauthor,Bclass)values('0003','���μ�','��ж�','����')
    	//String sql = "insert into �û���Ϣ�� (name,password,address,phone) values('"+name+"','"+type+"','"+count+"','"+price+"')";
    	//String toInsert = "insert flowerInfo(Bno,Bname,Bauthor,Bclass)values";
    	String toInsert = "insert into Orderinformation (FlowerName,Onum,price,Zongjia,date,�û���) values";
    	toInsert = toInsert + "('" + list.get(0) + "','" + list.get(1) + "','" + list.get(2)
    	+ "','" + list.get(3) + "','" + list.get(4) + "','" + list.get(5) + "')";
    	try {
    		pstt = dbConn.prepareStatement(toInsert);
    		success = pstt.executeUpdate();
    		
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }
    
    //���»��ܿ��
    public void updateFlowerNummethod(ArrayList list) {
    	
        String sql = "UPDATE Flower SET num = num - "+list.get(1)+" where name = '"+list.get(0)+"'";
        System.out.println(sql);

        try {
            pstt = dbConn.prepareStatement(sql);
            Num = pstt.executeUpdate();
            success = 1;
            if(Num>=0) {
            	System.out.println("���¿��ɹ�");
            	System.out.println(Num);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }
    
    //���»��ܿ��
    public void updateCFlowerNummethod(ArrayList list) {
    	
    	String sql = "UPDATE Flower SET num = num + "+list.get(2)+" where name = '"+list.get(1)+"'";
    	System.out.println(sql);
    	
    	try {
    		pstt = dbConn.prepareStatement(sql);
    		Num = pstt.executeUpdate();
    		success = 1;
    		if(Num>=0) {
    			System.out.println("���¿��ɹ�");
    			System.out.println(Num);
    		}
    		
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }

    //�޸��û���Ϣ
    public void updateUsermethod(ArrayList list) {
    	//update �û���Ϣ�� set password = '123' from (select name,password  from �û���Ϣ�� where name = 'Tom' and password = '000') as t
        //String toUpdate = "update �û���Ϣ�� set password=";
        //toUpdate = toUpdate + "'" + list.get(1) + "',Bauthor='" + list.get(2) + "',Bclass='"
                //+ list.get(3) + "' where Bno='" + list.get(0) + "'";
        String toUpdate = "update �û���Ϣ�� set password = ";
        toUpdate = toUpdate + "'" + list.get(2) + "' from (select name,password  from �û���Ϣ�� where name = '" + list.get(0) + "' and password = '"+list.get(1)+"') as t";
        String sql = "update �û���Ϣ�� set password = '" + list.get(1) + "',address = '" + list.get(2) + "',phone = '"
				+ list.get(3) + "'where name = '" + list.get(0) + "'";
        System.out.println(sql);

        try {
            pstt = dbConn.prepareStatement(sql);
            success = pstt.executeUpdate();
            //success = 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }
    
    
    
    //�޸Ļ�����Ϣ
    public void updateFlowermethod(ArrayList list) {
    	
        String sql = "update flower set type = '"+list.get(1)+"',num = '"+list.get(2)+"',price = '"+list.get(3)+"'where name = '"+list.get(0)+"'";
        System.out.println(sql);

        try {
            pstt = dbConn.prepareStatement(sql);
            success = pstt.executeUpdate();
            success = 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }
    
    //�޸Ķ�����Ϣ /////////////////////////////////������
    public void updateOrdermethod(ArrayList list) {
    	
    	String sql = "update Orderinformation set FlowerName = '"+list.get(1)+"',Onum = '"+list.get(2)+"',price = '"+list.get(3)+"',Zongjia = '"+list.get(4)+"',date = '"+list.get(5)+"',�û��� = '"+list.get(6)+"'where Ono = '"+list.get(0)+"'";
    	System.out.println(sql);
    	
    	try {
    		pstt = dbConn.prepareStatement(sql);
    		success = pstt.executeUpdate();
    		success = 1;
    		
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }
    
    
    
    //�޸��������ݷ���
    public void updatemethod(ArrayList list) {
    	//update �û���Ϣ�� set password = '123' from (select name,password  from �û���Ϣ�� where name = 'Tom' and password = '000') as t
        //String toUpdate = "update �û���Ϣ�� set password=";
        //toUpdate = toUpdate + "'" + list.get(1) + "',Bauthor='" + list.get(2) + "',Bclass='"
                //+ list.get(3) + "' where Bno='" + list.get(0) + "'";
        String toUpdate = "update �û���Ϣ�� set password = ";
                //+'123'+ from (select name,password  from �û���Ϣ�� where name = 'Tom' and password = '000') as t";
        toUpdate = toUpdate + "'" + list.get(2) + "' from (select name,password  from �û���Ϣ�� where name = '" + list.get(0) + "' and password = '"+list.get(1)+"') as t";

        System.out.println(toUpdate);

        try {
            pstt = dbConn.prepareStatement(toUpdate);
            success = pstt.executeUpdate();
            success = 1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }

    
    //ɾ���û����ݷ���
    public void deleteUsermethod(ArrayList list){
        //delete from flowerInfo where Bname='���μ�'
        String toDelete="delete from �û���Ϣ�� where name=";
        toDelete=toDelete+"'"+list.get(0)+"'";
        try {
            pstt = dbConn.prepareStatement(toDelete);
            success = pstt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }
    
    //ɾ���������ݷ���
    public void deleteFlowermethod(ArrayList list){
    	//delete from flowerInfo where Bname='���μ�'
    	String toDelete="delete from flower where name=";
    	toDelete=toDelete+"'"+list.get(0)+"'";
    	try {
    		pstt = dbConn.prepareStatement(toDelete);
    		success = pstt.executeUpdate();
    		
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }
    
    //ɾ���������ݷ���
    public void deleteOrdermethod(ArrayList list){
    	//delete from flowerInfo where Bname='���μ�'
    	String toDelete="delete from Orderinformation where Ono=";
    	int a = Integer.parseInt((String) list.get(0));
    	System.out.println(a);
    	toDelete=toDelete+"'"+a+"'";
    	try {
    		pstt = dbConn.prepareStatement(toDelete);
    		success = pstt.executeUpdate();
    		success = 1;
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }
    
    //ȡ���������ݷ���
    public void cancelOrdermethod(ArrayList list){
    	//delete from flowerInfo where Bname='���μ�'
    	String toDelete="delete from Orderinformation where Ono=";
    	int a = Integer.parseInt((String) list.get(0));
    	System.out.println(a);
    	toDelete=toDelete+"'"+a+"'";
    	try {
    		pstt = dbConn.prepareStatement(toDelete);
    		success = pstt.executeUpdate();
    		success = 1;
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }
}
