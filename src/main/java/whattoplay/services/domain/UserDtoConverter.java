/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whattoplay.services.domain;

import whattoplay.domain.dto.UserDto;
import whattoplay.domain.entities.UserEntity;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Andrzej
 */
@FunctionalInterface
public interface UserDtoConverter {
    UserDto convert(UserEntity from);
 
    default Collection<UserDto> convertAll(Collection<UserEntity> fElements){
        return fElements.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
