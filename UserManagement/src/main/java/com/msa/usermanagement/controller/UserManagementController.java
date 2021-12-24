package com.msa.usermanagement.controller;

import com.msa.usermanagement.model.RoleType;
import com.msa.usermanagement.model.User;
import com.msa.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("users")
public class UserManagementController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("{id}")
    public User getUser(@PathVariable int id){
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다.");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자는 없습니다.");
            }
        });
        return user;
    }

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public String postUser(User user){  // form tag를 이용해서 body의 내용을 가져온다
        System.out.println(user.toString());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return user.toString();
    }

    // password
    @Transactional // 함수 종료시에 자동으로 commit이 된다. 더티 체킹으로 data update 한다.
    @PutMapping("{id}")
    public User putUser(@PathVariable int id, @RequestBody User requestUser){ // json을 입력으로 받기 위해서 RequestBody를 사용 Spring이 자동으로 object로 변환해 줌

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저는 없습니다.(" + id +")");
        });
        user.setPassword(requestUser.getPassword());

        return user;
    }



    @DeleteMapping("/{id}")
    public String deleteTest(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제 되었습니다. (" + id + ")";
    }



}
