package com.webisystems.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Stream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.webisystems.app.DAO.AuthorRepository;
import com.webisystems.app.bo.Author;

@RestController
public class AuthorController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private AuthorRepository authorService;

 
	private static final String MODEL_NAME = "author";
	
	
	  @Autowired
	  private  RestTemplate restTemplate;
	  
	  @Autowired
	  private MessageSource messageSource;
	  
	  @GetMapping(value = "/")
	  public String hello() throws InterruptedException {
	     Thread.sleep(3000);
	     return "Welcome Hystrix";
	  }
	  
	  @GetMapping("/local")
	  public String getLocale( Locale locale ) {
		  return  messageSource.getMessage("welcome.text",null, locale);
	  }

	   @GetMapping(value = "/template/products")
	   public String getProductList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8080/author", HttpMethod.GET, entity, String.class).getBody();
	   }
	   
	   
	   @PostMapping(value = "/upload", 
			      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
			   public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
			      File convertFile = new File("/var/tmp/"+file.getOriginalFilename());
			      convertFile.createNewFile();
			      FileOutputStream fout = new FileOutputStream(convertFile);
			      fout.write(file.getBytes());
			      fout.close();
			      return "File is upload successfully";
			   }
	   
	   
	   @GetMapping(value = "/download") 
	   public ResponseEntity<Object> downloadFile() throws IOException  {
	      String filename = "/var/tmp/re.txt";
	      File file = new File(filename);
	      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	      HttpHeaders headers = new HttpHeaders();
	         
	      headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	      headers.add("Pragma", "no-cache");
	      headers.add("Expires", "0");
	         
	      ResponseEntity<Object> 
	      responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
	         MediaType.parseMediaType("application/txt")).body(resource);
	         
	      return responseEntity;
	   }
	   
@GetMapping("/goto")
public ResponseEntity<Object> dowloadFile(@PathVariable(required = false) String file) throws IOException{
	try (Stream<Path> paths = Files.walk(Paths.get("/Users/zouhairkasmi/eclipse-workspace/spring-boot-jpa"))) {
	    paths
	        .filter(Files::isDirectory)
					.forEach(e -> LOG.info("File name " + e.getFileName().toString()));
	  
	    return ResponseEntity.ok(paths);
	} 
}


	@GetMapping("/" + MODEL_NAME + "/{id}")
	public ResponseEntity<Object> get(@PathVariable Long id) {
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>(authorService.getOne(id), HttpStatus.OK);
		return res;
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/" + MODEL_NAME + "")
	public ResponseEntity<Object> list() {
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>(authorService.findAll(), HttpStatus.OK);
		return res;
	}

	@PostMapping("/" + MODEL_NAME + "/add")
	public ResponseEntity<Object> add(@RequestBody Author object) {
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>(authorService.save(object), HttpStatus.CREATED);
		return res;
	}

	@DeleteMapping("/" + MODEL_NAME + "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		authorService.deleteById(id);
		ResponseEntity<Object> res = 
				new ResponseEntity<Object>(HttpStatus.OK);
		return res;
	}
	
	@GetMapping(value = "/sendemail")
	public String sendEmail() throws AddressException, MessagingException, IOException {
	   sendmail();
	   return "Email sent successfully";   
	}
	
	private void sendmail() throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("tutorialspoint@gmail.com", "<your password>");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("tutorialspoint@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tutorialspoint@gmail.com"));
		   msg.setSubject("Tutorials point email");
		   msg.setContent("Tutorials point email", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("/var/tmp/image19.png");
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}

}
