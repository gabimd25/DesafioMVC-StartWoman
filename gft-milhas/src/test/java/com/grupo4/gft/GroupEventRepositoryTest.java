package com.grupo4.gft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.repositories.GroupEventRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class GroupEventRepositoryTest {

	@Autowired
	private GroupEventRepository groupEventRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateGroupEvent() {
		GroupEvent groupEvent = new GroupEvent();
		
		
		groupEvent.setName("Grupo pesquisa");
		
		GroupEvent saveGroupEvent = groupEventRepository.save(groupEvent);
		
		GroupEvent existGroupEvent = entityManager.find(GroupEvent.class, saveGroupEvent.getId());
		
		assertThat(existGroupEvent.getName().equalsIgnoreCase(groupEvent.getName()));
		
}
	@Test
	public void testFindGroupEventByName() {
		String name = "Grupo pesquisa";
		
		GroupEvent groupEvent = (GroupEvent) groupEventRepository.findByName(name);
		
		assertThat(groupEvent).isNotNull();
		
	}

}
