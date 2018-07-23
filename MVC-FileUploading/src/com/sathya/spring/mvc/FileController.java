package com.sathya.spring.mvc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController 
{
	@RequestMapping(value="fileupload" ,method=RequestMethod.GET)
	public ModelAndView getUploadPage()
	{
		return new  ModelAndView("fileUpload");
	}
	
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ModelAndView uploadFile(@RequestParam("file1")MultipartFile f1,HttpServletRequest request)
	{
		String fail="";
		String success="";
		if(f1.getSize()!=0)
		{ System.out.println("upload....");
			if(f1.getContentType().equals("text/xml"))
			{
				try{
						FileOutputStream fos=new FileOutputStream("E:/"+f1.getOriginalFilename());
						fos.write(f1.getBytes());
						success="You File"+f1.getOriginalFilename()+"Uploaded successfully";
						fos.close();
				   }
				catch(FileNotFoundException e)
				{
						e.printStackTrace();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				
			}
			else
			{
				fail="The file should be xml only!!!";
			}
		}
		else
		{
			System.out.println("file should not empty");
			fail="The file should not be Empty!!!!";
		}
		if(success.length()>1)
		{
			return new ModelAndView("success","message",success);
		}
		else
		{ System.out.println("file some problem");
			return new ModelAndView("fileUpload","message",fail);
		}
	}
}
