package com.dell.tsp.notification.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dell.tsp.notification.VO.EmailDocumentTemplate;

@RunWith(MockitoJUnitRunner.class)
public class EmailRepositoryTest {

	@Mock
	private EmailRepository emailRepositoryMock;
	

	@InjectMocks
	private EmailDocumentTemplate emailDocumentTemplate;
	
	@Mock
	private List<EmailDocumentTemplate> list;
	
	@Test
	public void testSaveMethod() {
		when(emailRepositoryMock.save(emailDocumentTemplate)).thenReturn(emailDocumentTemplate);
		EmailDocumentTemplate returnedOption = emailRepositoryMock.save(emailDocumentTemplate);
    	assertThat(returnedOption,equalTo(emailDocumentTemplate));
	}
	
	@Test
	public void testFindBySubscriberId() {
		when(emailRepositoryMock.findBySubscriberId(anyInt())).thenReturn(list);
		List<EmailDocumentTemplate> returnedList = emailRepositoryMock.findBySubscriberId(anyInt());
    	assertThat(returnedList,equalTo(list));
	}
}
