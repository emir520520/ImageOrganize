package ca.fangyux;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ImageOrganize {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入需要整理的文件夹路径: ");
        String source=sc.nextLine();

        System.out.println("请输入最终整理好的文件夹路径（需要自己提前创建好）: ");
        String destination=sc.nextLine();

        File repoSource=new File(source);
        File repoDestination=new File(destination);

        //-----------------------
        Set<String> dateSet=new HashSet<>();

        String suffix;
        String dateTime;
        java.text.SimpleDateFormat df;
        String[] infos;
        int count=0;
        for (File file : repoSource.listFiles()) {
            suffix=file.getName().substring(file.getName().lastIndexOf(".")).trim();

            if(".png".equals(suffix) || ".jpg".equals(suffix) || ".jpeg".equals(suffix)){
                df = new java.text.SimpleDateFormat("yyyy-MM-dd");
                dateTime=df.format(new Date(file.lastModified())).trim();
                infos=dateTime.split("-");

                //---------------------------------------------
                String year=infos[0];
                String month=infos[1];
                String day=infos[2];
                File repoDes= new File(repoDestination.getAbsolutePath() + File.separator + year+ File.separator + month+"月"+ File.separator + day+"日");

                if(!dateSet.contains(dateTime)){
                    repoDes.mkdirs();
                    dateSet.add(dateTime);
                }

                //----------------Copy Image
                File fileDes=new File(repoDes.getAbsolutePath()+File.separator+file.getName());
                FileUtils.copyFile(file,fileDes);
                count++;
            }
        }
        System.out.println("一共整理了"+count+"张照片");
    }
}