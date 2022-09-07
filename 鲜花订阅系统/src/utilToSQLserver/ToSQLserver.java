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
    String toSelectUser = "select * from 用户信息表 where name=";
    String toSelectDD = "select * from Orderinformation where Ono=";
	
   
    public ToSQLserver() {
        try {
            //1.加载驱动
            //Class.forName方法的作用,就是初始化给定的类.而我们给定的MySQL的Driver类中,
            // 它在静态代码块中通过JDBC的DriverManager注册了一下驱动.我们也可以直接使用JDBC的驱动管理器注册mysql驱动.
            // 从而代替使用Class.forName.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.连接
            dbConn = DriverManager.getConnection(dbURL, name, passwd);
            System.out.println("连接数据库成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //检查花卉库存                          未使用
    public ArrayList<String> selectFlowerNummethod(ArrayList list) {
    	
        String sql = "select num from Flower where name = '"+list.get(0)+"'";
        System.out.println(sql);

        try {
            pstt = dbConn.prepareStatement(sql);
            result = pstt.executeQuery();
            success = 1;
            Num = Integer.parseInt(result.getString("num"));
            System.out.println(Num);
            System.out.println("查询成功");
    		
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
    

    //查找花卉
    public ArrayList<String> selectFlowermethod(String text1) {
        String uninSelect = toSelectFlower + "'" + text1 + "'";
        System.out.println(uninSelect);
        try {
            pstt = dbConn.prepareStatement(uninSelect);
            result = pstt.executeQuery();
            System.out.println("查询成功");

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
    
    
    
    //查找订单
    public ArrayList<String> selectDDmethod(String text1) {
    	String uninSelect = toSelectDD + "'" + text1 + "'";
    	System.out.println(uninSelect);
    	try {
    		pstt = dbConn.prepareStatement(uninSelect);
    		result = pstt.executeQuery();
    		System.out.println("查询成功");
    		
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
    
    //查找用户订单
    public ArrayList<String> selectUserDDmethod(String text1) {
    	//String uninSelect = toSelectDD + "'" + text1 + "'";
    	String sql = "select * from Orderinformation where FlowerName ='"+text1+"'";
    	System.out.println(sql);
    	try {
    		pstt = dbConn.prepareStatement(sql);
    		result = pstt.executeQuery();
    		System.out.println("查询成功");
    		
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

    
    
    //查找用户
    public ArrayList<String> selectUsermethod(String text1) {
    	String uninSelect = toSelectUser + "'" + text1 + "'";
    	System.out.println(uninSelect);
    	try {
    		pstt = dbConn.prepareStatement(uninSelect);
    		result = pstt.executeQuery();
    		System.out.println("查询成功");
    		
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
    
    
    
    
    //用户登录查询
    public boolean loginmethod(ArrayList list) {
    	//String sql = "select * from Admin where name = '"+name+"'and password = '"+password+"'";//'"+name+"'
    	//String toInsert = "insert into 用户信息表 (name,password,address,phone) values";
    	//String toInsert = "select * from 用户信息表 where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'";
        //toInsert = toInsert + "('" + list.get(0) + "'and password = '" + list.get(1) + "')";
    	boolean res = false;
    	try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
			System.out.println("连接数据库成功！");
			Statement stt = conn.createStatement();
			String sql = "select * from 用户信息表 where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'
			ResultSet rs = stt.executeQuery(sql);
			
			if(rs.next())
				res = true;
				

			System.out.println(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return res; 
    }
    
    
    //管理员用户登录查询
    public boolean Adminloginmethod(ArrayList list) {
    	//String sql = "select * from Admin where name = '"+name+"'and password = '"+password+"'";//'"+name+"'
    	//String toInsert = "insert into 用户信息表 (name,password,address,phone) values";
    	//String toInsert = "select * from Admin where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'";
    	//toInsert = toInsert + "('" + list.get(0) + "'and password = '" + list.get(1) + "')";
    	boolean res = false;
    	try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MyDB","sa","root");
			System.out.println("连接数据库成功！");
			Statement stt = conn.createStatement();
			String sql = "select * from Admin where name = '"+list.get(0)+"'and password = '"+list.get(1)+"'";//'"+name+"'";
			ResultSet rs = stt.executeQuery(sql);
			
			if(rs.next())
				res = true;
				

			System.out.println(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return res; 
    }
    
    
    
    //插入用户数据方法
    public void insertUsermethod(ArrayList list) {
        //insert flowerInfo(Bno,Bname,Bauthor,Bclass)values('0003','西游记','吴承恩','神话类')
    	//String sql = "insert into 用户信息表 (name,password,address,phone) values('"+name+"','"+type+"','"+count+"','"+price+"')";
        //String toInsert = "insert flowerInfo(Bno,Bname,Bauthor,Bclass)values";
    	String toInsert = "insert into 用户信息表 (name,password,address,phone) values";
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
    
    //插入花卉数据方法
    public void insertFlowermethod(ArrayList list) {
    	//insert flowerInfo(Bno,Bname,Bauthor,Bclass)values('0003','西游记','吴承恩','神话类')
    	//String sql = "insert into 用户信息表 (name,password,address,phone) values('"+name+"','"+type+"','"+count+"','"+price+"')";
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
    
    //插入订单数据方法
    public void insertOrdermethod(ArrayList list) {
    	//insert flowerInfo(Bno,Bname,Bauthor,Bclass)values('0003','西游记','吴承恩','神话类')
    	//String sql = "insert into 用户信息表 (name,password,address,phone) values('"+name+"','"+type+"','"+count+"','"+price+"')";
    	//String toInsert = "insert flowerInfo(Bno,Bname,Bauthor,Bclass)values";
    	String toInsert = "insert into Orderinformation (FlowerName,Onum,price,Zongjia,date,用户名) values";
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
    
    //更新花卉库存
    public void updateFlowerNummethod(ArrayList list) {
    	
        String sql = "UPDATE Flower SET num = num - "+list.get(1)+" where name = '"+list.get(0)+"'";
        System.out.println(sql);

        try {
            pstt = dbConn.prepareStatement(sql);
            Num = pstt.executeUpdate();
            success = 1;
            if(Num>=0) {
            	System.out.println("更新库存成功");
            	System.out.println(Num);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }
    
    //更新花卉库存
    public void updateCFlowerNummethod(ArrayList list) {
    	
    	String sql = "UPDATE Flower SET num = num + "+list.get(2)+" where name = '"+list.get(1)+"'";
    	System.out.println(sql);
    	
    	try {
    		pstt = dbConn.prepareStatement(sql);
    		Num = pstt.executeUpdate();
    		success = 1;
    		if(Num>=0) {
    			System.out.println("更新库存成功");
    			System.out.println(Num);
    		}
    		
    	} catch (SQLException throwables) {
    		throwables.printStackTrace();
    		success = 0;
    	}
    }

    //修改用户信息
    public void updateUsermethod(ArrayList list) {
    	//update 用户信息表 set password = '123' from (select name,password  from 用户信息表 where name = 'Tom' and password = '000') as t
        //String toUpdate = "update 用户信息表 set password=";
        //toUpdate = toUpdate + "'" + list.get(1) + "',Bauthor='" + list.get(2) + "',Bclass='"
                //+ list.get(3) + "' where Bno='" + list.get(0) + "'";
        String toUpdate = "update 用户信息表 set password = ";
        toUpdate = toUpdate + "'" + list.get(2) + "' from (select name,password  from 用户信息表 where name = '" + list.get(0) + "' and password = '"+list.get(1)+"') as t";
        String sql = "update 用户信息表 set password = '" + list.get(1) + "',address = '" + list.get(2) + "',phone = '"
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
    
    
    
    //修改花卉信息
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
    
    //修改订单信息 /////////////////////////////////有问题
    public void updateOrdermethod(ArrayList list) {
    	
    	String sql = "update Orderinformation set FlowerName = '"+list.get(1)+"',Onum = '"+list.get(2)+"',price = '"+list.get(3)+"',Zongjia = '"+list.get(4)+"',date = '"+list.get(5)+"',用户名 = '"+list.get(6)+"'where Ono = '"+list.get(0)+"'";
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
    
    
    
    //修改密码数据方法
    public void updatemethod(ArrayList list) {
    	//update 用户信息表 set password = '123' from (select name,password  from 用户信息表 where name = 'Tom' and password = '000') as t
        //String toUpdate = "update 用户信息表 set password=";
        //toUpdate = toUpdate + "'" + list.get(1) + "',Bauthor='" + list.get(2) + "',Bclass='"
                //+ list.get(3) + "' where Bno='" + list.get(0) + "'";
        String toUpdate = "update 用户信息表 set password = ";
                //+'123'+ from (select name,password  from 用户信息表 where name = 'Tom' and password = '000') as t";
        toUpdate = toUpdate + "'" + list.get(2) + "' from (select name,password  from 用户信息表 where name = '" + list.get(0) + "' and password = '"+list.get(1)+"') as t";

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

    
    //删除用户数据方法
    public void deleteUsermethod(ArrayList list){
        //delete from flowerInfo where Bname='西游记'
        String toDelete="delete from 用户信息表 where name=";
        toDelete=toDelete+"'"+list.get(0)+"'";
        try {
            pstt = dbConn.prepareStatement(toDelete);
            success = pstt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = 0;
        }
    }
    
    //删除花卉数据方法
    public void deleteFlowermethod(ArrayList list){
    	//delete from flowerInfo where Bname='西游记'
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
    
    //删除订单数据方法
    public void deleteOrdermethod(ArrayList list){
    	//delete from flowerInfo where Bname='西游记'
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
    
    //取消订单数据方法
    public void cancelOrdermethod(ArrayList list){
    	//delete from flowerInfo where Bname='西游记'
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
