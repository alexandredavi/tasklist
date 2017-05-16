package br.com.tasklist.core.producer;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

/**
 * Producer para o model mapper, utilizado para fazer o mapeamento entre entidades e DTO's.
 */

@ApplicationScoped
public class ModelMapperProducer {

    @Produces
    @RequestScoped
    public static ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

}