package com.example.cinderella.service;

import com.example.cinderella.domain.chat.Chat;
import com.example.cinderella.domain.chat.ChatRepository;
import com.example.cinderella.domain.user.Gender;
import com.example.cinderella.domain.user.Users;
import com.example.cinderella.domain.user.UsersRepository;
import com.example.cinderella.web.dto.ChatResponseDto;
import com.example.cinderella.web.dto.ChatSaveRequestDto;
import com.example.cinderella.web.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<ChatResponseDto> findAllByStart(String start) {
        List<Chat> chatlist = chatRepository.findAllByStart(start);
        // 반환해줄 새로운 entity
        List<Chat> entity = new ArrayList<>();
        // 한글로 변경할 시작지점 map 생성
        Map<String, String> startName = Map.of(
                "school", "부산대정문",
                "bank", "부산은행",
                "subway", "부산대역"
        );
        // start에 해당하는 모든 방들의 시간과 시작지점을 변경
        for (Chat chat : chatlist) {
            // 시간 계산
            int time = chat.getTime();
            int hour = time / 60;
            String minutes = String.format("%02d", time % 60);
            String setAmPm;
            String calcTime = null;
            if (hour < 12) {
                setAmPm = "am";
                calcTime = setAmPm + " " + String.format("%02d", hour) + " : " + minutes;
            } else if (12 <= hour && hour <= 24) {
                setAmPm = "pm";
                calcTime = setAmPm + " " + String.format("%02d", hour) + " : " + minutes;
            }
            // 시간 저장
            chat.setStartAndTime(startName.get(start), calcTime);
            entity.add(chat);
            // 시작지점 변경
        }
        // entity를 dto 형태로 반환
        return entity.stream()
                .map(ChatResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 방 생성 : host,start,dest,time을 넘겨받아 entity로 바꿔서 저장
     */
    @Transactional
    public void saveChat(ChatSaveRequestDto requestDto){
        chatRepository.save(requestDto.toEntity());
    }

    /**
     * 유저 이메일에 따른 성별 유무 체크
     */
    @Transactional(readOnly = true)
    public Boolean findGender(String email) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 정보가 없습니다. email=" + email));
        if (users.getGender() == null) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 회원가입 == 유저의 성별을 저장
     */
    @Transactional
    public void signUp(String email, SignUpRequestDto signUpRequestDto) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 정보가 없습니다. email=" + email));

        // Dto에서 정보를 가져와서 업데이트시킴
        String name = signUpRequestDto.getName();
        Gender gender = signUpRequestDto.getGender();
        users.signUp(name, gender);
    }
}

