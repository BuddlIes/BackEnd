package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.UserInfo;

import java.util.*;

//회원 레포지토리 메모리 구현체
public class MemoryUserRepository /* implements UserRepository*/{
//    private static Map<Long, UserInfo> store = new HashMap<>();
//    private static long sequence = 0L;
//    @Override
//    public UserInfo save(UserInfo users) {
//        users.setUserNum(++sequence);
//        store.put(users.getUserNum(), users);
//        return users;
//    }
//    @Override
//    public Optional<UserInfo> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    @Override
//    public List<UserInfo> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    @Override
//    public Optional<UserInfo> findByName(String name) {
//        return store.values().stream()
//                .filter(member -> member.getName().equals(name))
//                .findAny();
//    }
//    public void clearStore() {
//        store.clear();
//    }
}
