package com.hbq.teacher_plus.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
@Component
public class FastDFSUploadImg {
	@Value("${FastDFS.propertiesPath}")
	String propertiesPath;
	@Value("${FastDFS.imgPrefixPath}")
	String imgPrefixPath;

	public  String imgUpload(MultipartFile imgFile) throws FileNotFoundException, IOException, Exception{
		//1.加载配置文件ip端口
		ClientGlobal.init(propertiesPath);
		//2.创建管理端对象
		TrackerClient trackerClient=new TrackerClient();
		//3.获取连接
		TrackerServer trackerServer=trackerClient.getConnection();
		//4.创建储存端对象
		StorageClient1 storageClient1=new StorageClient1(trackerServer, null);
		//5.创建文件属性-对象数组
		NameValuePair[] meta_list=new NameValuePair[1];
		meta_list[0]=new NameValuePair("filename",imgFile.getOriginalFilename());
		//6.上传文件
		String path=storageClient1.upload_file1(imgFile.getBytes(), "jpg", meta_list);
		//拼接fastdfs的IP前缀得到图片真实地址
		path=imgPrefixPath+path;
		
		System.out.println(path);
		//返回一个真实地址		
		return path;
	}
}
