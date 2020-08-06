package doremi;

import doremi.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ViewViewHandler {


    @Autowired
    private ViewRepository viewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMenuRegistered_then_CREATE_1 (@Payload MenuRegistered menuRegistered) {
        try {
            if (menuRegistered.isMe()) {
                // view 객체 생성
                View view = new View();
                // view 객체에 이벤트의 Value 를 set 함
                view.setMenuId(menuRegistered.getId());
                view.setMenuName(menuRegistered.getMenuName());
                view.setMenuPrice(menuRegistered.getPrice());
                view.setDescription(menuRegistered.getDescription());
                // view 레파지 토리에 save
                viewRepository.save(view);
                System.out.println("##### view CREATE COMPLETE");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenRated_then_UPDATE_1(@Payload Rated rated) {
        try {
            if (rated.isMe()) {
                // view 객체 조회
                List<View> viewList = viewRepository.findByMenuId(rated.getMenuId());
                for(View view : viewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    if(view.getMenuId().equals(rated.getMenuId())){
                        view.setScore(rated.getScore());
                        // view 레파지 토리에 save
                        viewRepository.save(view);
                    }else{
                        System.out.println("##### view rated NOT EXISTS ");
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCancelled_then_DELETE_1(@Payload Cancelled cancelled) {
        try {
            if (cancelled.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                List<View> viewList= viewRepository.findByMenuId(cancelled.getMenuId());
                if(!viewList.isEmpty()){
                    System.out.println("##### whenCancelled_then_DELETE_1 View Delete"+viewList);
                    viewRepository.deleteById(viewList.get(0).getId());
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
