package cn.edu.henau.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.henau.service.TestSpring;

@Path("/demo")
public class RestfulDemo {
	@Autowired
	private TestSpring testSpringImpl;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello World!";
	}

//	@GET
//	@Path("/{username}")
//	// @Produces("text/plain;charset=UTF-8")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String sayHelloToUTF8(@PathParam("username") String username) {
//		return "Hello " + username;
//	}
	@GET
	@Path("/testIoc")
	public String testIoc(){
//		Seckill seckill =new Seckill();
		return testSpringImpl.sayHello();
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User sayHelloToJson(@QueryParam("username") String username) {
		if (StringUtils.isEmpty(username)) {
			username = "none";
		}
		User user = new User();
		user.setId(1);
		user.setName(username);
		return user;
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		user.setId(2);
		user.setName("update name:" + user.getName());
		return user;
	}
}