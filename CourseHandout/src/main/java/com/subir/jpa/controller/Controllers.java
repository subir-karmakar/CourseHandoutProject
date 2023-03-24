package com.subir.jpa.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.subir.jpa.services.FileService;

import jakarta.servlet.http.HttpSession;

import com.subir.jpa.dao.CourseRepo;
import com.subir.jpa.dao.UsersRepo;
import com.subir.jpa.model.Course;
import com.subir.jpa.model.Users;

@Controller
public class Controllers {
	@Autowired
	CourseRepo repo;
	
	@Autowired
	UsersRepo urepo;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping("/")
	public ModelAndView login() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
		
	}
	@RequestMapping("verify")
	public ModelAndView verify(Users user,HttpSession session) {
		List<Users> login= (List<Users>) urepo.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		System.out.print(login);
		System.out.print(user.getUsername()+" "+user.getPassword());
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj","invalid");
		mv.setViewName("login");
		for(Users result:login)
		{
			if(result.getUsername()!=null)
			{
				mv.addObject("obj",user);
				mv.setViewName("index");
				session.setAttribute("suser", user);
			}
		}
		return mv;
	}
	@RequestMapping("editSetting")
	public ModelAndView editSetting(@RequestParam("password") String password,HttpSession session) {
		Users suser = (Users)session.getAttribute("suser");
		List<Users> userCheck = (List<Users>) urepo.findByUsername(suser.getUsername());
		Users newUser = new Users();
		for(Users result:userCheck) {
			newUser.setId(result.getId());
			newUser.setUsername(result.getUsername());
			newUser.setPassword(password);
		}
		urepo.save(newUser);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj","updated");
		mv.setViewName("settings");
		return mv;
	}
	@RequestMapping("register")
	public ModelAndView register(Users user) {
		List<Users> userCheck = (List<Users>) urepo.findByUsername(user.getUsername());
		ModelAndView mv = new ModelAndView();
		int flag=0;
		for(Users result:userCheck)
		{
			System.out.print(result.getUsername());
			if(result.getUsername()!=null)
			{
				flag=1;
			}
		}
		if(flag==1) {
			mv.addObject("obj","invalid");
			mv.setViewName("addUser");
		}
		else {
			Users fresult = urepo.save(user);
			mv.addObject("obj","newuser");
			mv.setViewName("addUser");
		}
		return mv;
	}
	
	@RequestMapping("allUser")
	public ModelAndView allUser(HttpSession session) {
		List<Users> users = (List<Users>) urepo.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj",users);
		mv.setViewName("allUser");
		return mv;
	}
	
	@RequestMapping("editUser/{id}")
	public ModelAndView editUser(@PathVariable("id") String id,HttpSession session) {
		//Users suser = (Users)session.getAttribute("suser");
		//List<Users> userCheck = (List<Users>) urepo.findByUsername(suser.getUsername());
		Users user = urepo.findById(id).orElse(new Users());
		ModelAndView mv = new ModelAndView();
		mv.addObject("user",user);
		mv.setViewName("editUser");
		return mv;
	}
	
	@RequestMapping("updateUser")
	public ModelAndView updateUser(Users user,HttpSession session) {
		urepo.save(user);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj",user);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("deleteUser/{id}")
	public ModelAndView deleteUser(@PathVariable("id") String id,HttpSession session) {
		urepo.deleteById(id);
		ModelAndView mv = new ModelAndView();
		//mv.addObject("user",user);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		
		Users suser = (Users)session.getAttribute("suser");
		if(suser!=null)
		{  
		  session.removeAttribute("suser");
		  session.invalidate();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
		
	}
	@RequestMapping("addHandout")
	public ModelAndView addHandout(Course course,HttpSession session) {
		repo.save(course);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj",course);
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping("editHandout/{id}")
	public ModelAndView editHandout(@PathVariable("id") Long id,HttpSession session) {
		//repo.save(course);
		//List<Course> course = (List<Course>) repo.findById((id.intValue())).orElse(new Course());
		Course course = repo.findById((id.intValue())).orElse(new Course());
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj",course);
		mv.setViewName("editHandout");
		return mv;
	}
	@RequestMapping("deleteHandout/{id}")
	public ModelAndView deleteHandout(@PathVariable("id") Long id,HttpSession session) {
		repo.deleteById(id.intValue());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping("allHandout")
	public ModelAndView allHandout(HttpSession session) {
		List<Course> course = (List<Course>) repo.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj",course);
		mv.setViewName("tables");
		return mv;
	}
	/*public ModelAndView home(Alien alien) {
		repo.save(alien);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj",alien);
		mv.setViewName("Home");
		return mv;
	}*/
	@PostMapping("imgUpload")
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		String uploadPath = "imagesa/";
		String path = "./src/main/webapp/"+uploadPath;
		String fileName = this.fileService.uploadImage(path, file);
		return ResponseEntity.ok("{ \"location\": "+"\""+uploadPath+fileName+"\" }");
	}
	/*Lastest Working Example with dummy data */
	/*public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file) throws IOException {
		String uploadPath = "imagesa/";
		String path = "./src/main/webapp/"+uploadPath;
		String name = file.getOriginalFilename();
		String filePath = path + File.separator+file;
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return ResponseEntity.ok("{ \"location\": "+"\""+uploadPath+name+"\" }");
	}*/
	/*public ResponseEntity<String> saveImage() throws IOException {
		String path = "img/";
		String name = "student.jpg";
		
		return ResponseEntity.ok("{ \"location\": "+"\""+path+name+"\" }");
	}*/
	/*public String saveImage(@RequestParam("file") MultipartFile file) throws IOException {
		String path = "images/";
		String name = file.getOriginalFilename();
		String filePath = path + File.separator+file;
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return ResponseEntity.ok("{ \"location\": "+"\""+path+name+"\" }");
	}*/
}
