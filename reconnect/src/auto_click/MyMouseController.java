package auto_click;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import javax.swing.*;

public class MyMouseController{
    
    private Dimension dim; //�洢��Ļ�ߴ�
    private Robot robot;//�Զ�������

    public MyMouseController(){
//        dim = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println("��Ļ��СΪ��" + dim.getWidth() + " " + dim.getHeight());
        try{
            robot = new Robot();
        }catch(AWTException e){
            e.printStackTrace();
        }
    }
    
    public void Move(int width,int heigh){    //����ƶ�����    
        System.out.println("enter Move()...");
//        Point mousepoint = MouseInfo.getPointerInfo().getLocation();
//          System.out.println("�ƶ�ǰ���꣺" + mousepoint.x + " " + mousepoint.y);
//          width += mousepoint.x;
//          heigh += mousepoint.y;
        try{
            robot.delay(3000);
            robot.mouseMove(width,heigh);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("�ƶ������꣺" + width + " " + heigh);
        robot.mousePress(InputEvent.BUTTON1_MASK);//��굥��
        robot.mouseRelease(InputEvent.BUTTON1_MASK);//��굥��
        robot.mousePress(InputEvent.BUTTON1_MASK);//��굥��
        robot.mouseRelease(InputEvent.BUTTON1_MASK);//��굥��
    }
    

    public static void main(String args[])throws Exception{
        
        
        
        MyMouseController mmc = new MyMouseController();
        
        System.out.println("mouse control start:");
        mmc.Move(500,500);//����Ϊ�������
        System.out.println("=======�ڶ����ƶ�=======");
        mmc.Move(812,884);
         
        System.out.println("mouse control stop.");
        
    }
}