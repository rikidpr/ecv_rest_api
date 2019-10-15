package an.dpr.ecv.resources.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import an.dpr.ecv.entities.Participant;

@Dependent
public class ParticipantMapper {

    private Mapper mapper;

	@PostConstruct
    void init() {
        mapper = DozerBeanMapperBuilder.buildDefault();
    }
    
    public ParticipantDTO map(Participant participant) {
        return mapper.map(participant, ParticipantDTO.class);
    }

    public List<ParticipantDTO> map(List<Participant> list) {
        return list == null ? null : list.stream().map(this::map).collect(Collectors.toList());
    }
    
}