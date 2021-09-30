package com.data.model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.validation.*;

public class AccountTest extends Account {

private ValidatorFactory vf = null;
private Validator validator = null;
private Account account = null;

	@Before
	public void setUp() throws Exception {
		vf = Validation.buildDefaultValidatorFactory();
		validator = vf.getValidator();	
		
		 account = new Account();
	}
	
	private static void validate(Validator validator, Account account)
	{
		Set<ConstraintViolation<Account>> violations = validator.validate(account);
		if (violations.isEmpty())
			{System.out.println("Valid account:"+ account.toString());		}
		else 
		{
			try {
				
				for  (ConstraintViolation<Account> violation: violations  ) 
				{
		System.out.println("Test Wrong Messages"+ violation.getPropertyPath()+", "
		+ violation.getMessage()+", Offending property: "+violation.getPropertyPath()
		+ ", Offending value: "+violation.getInvalidValue() );
		
		switch (violation.getMessage())
		{
			case "Cannot be 'null'":  assertEquals("Cannot be 'null'", violation.getMessage()); break;
			case "Cannot be 'missing'":  assertEquals("Cannot be 'missing'", violation.getMessage()); break;
			case "Length cannot be greater than 16 characters":  assertEquals("Length cannot be greater than 16 characters", violation.getMessage()); break;
			case "Cannot have 'all zeros'":  assertEquals("Cannot have 'all zeros'", violation.getMessage()); break;
			case "Cannot be alpha-numeric":  assertEquals("Cannot be alpha-numeric", violation.getMessage()); break;
			default:break;	
		}			
			
				}//for	
			} catch (ConstraintViolationException e)
				{
					System.out.println(new ConstraintViolationException(violations)+" "+e.getMessage());
					e.printStackTrace();
				}
			
		}
		
		
	}

	

	@Test
	public void testConstraintsZeros() {
		
		account.setAccount_number("00000");
		account.setBusiness_cd("11111");		
		validate(validator,account );
		
		
	
	}
	@Test
	public void testConstraintsAlphaNumeric() {
		account.setAccount_number("00A00");
		account.setBusiness_cd("11111");		
		validate(validator,account );
	
	}
	
	@Test
	public void testConstraintsNull() {
		account.setAccount_number(null);
		account.setBusiness_cd("11111");		
		validate(validator,account );
	
	}
	
	@Test
	public void testConstraintsSize17() {
		account.setAccount_number("4444444444444444444444444444444444");
		account.setBusiness_cd("11111");		
		validate(validator,account );
	
	}
	
	@Test
	public void testConstraintsMIssing() {
		account.setAccount_number("");
		account.setBusiness_cd("11111");		
		validate(validator,account );
	
	}
	
	
	@After
	public void tearDown() throws Exception {
	}

}
