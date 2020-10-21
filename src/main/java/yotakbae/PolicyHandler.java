package yotakbae;

import org.hibernate.engine.spi.IdentifierValue;
import yotakbae.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired
    DeliveryAggRepository deliveryAggRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_DeliverySetPol(@Payload Paid paid){

        if(paid.isMe()){
            //LJK
            DeliveryAgg delivery = new DeliveryAgg();
            delivery.setRequestId(paid.getRequestId());
            delivery.setStatus("BeforeChecked");
            delivery.setLocation("");
            delivery.setMemberId(paid.getMemberId());
            deliveryAggRepository.save(delivery);
            //LJK
            //System.out.println("##### listener DeliverySetPol : " + paid.toJson());
        }
    }

}
