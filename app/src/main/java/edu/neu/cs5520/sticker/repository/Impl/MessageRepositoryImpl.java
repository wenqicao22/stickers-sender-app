package edu.neu.cs5520.sticker.repository.Impl;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.neu.cs5520.sticker.model.Message;
import edu.neu.cs5520.sticker.repository.MessageRepository;

public class MessageRepositoryImpl implements MessageRepository{
    Map<String, List<Message>> sendMessageMap;
    Map<String, List<Message>> recvMessageMap;
    private DatabaseReference root;
    Gson gson;

    public MessageRepositoryImpl(){
        root = FirebaseDatabase.getInstance().getReference().getRoot();
        gson = new Gson();
        insertMessage(new Message("user1","user2","",""));
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator i = snapshot.getChildren().iterator();
                sendMessageMap = new HashMap<>();
                recvMessageMap = new HashMap<>();
                while(i.hasNext()){
                    String resp = (String) ((DataSnapshot) i.next()).getValue();
                    Message message = gson.fromJson(resp, Message.class);
                    String sender = message.getSender();
                    String recver = message.getReceiver();
                    sendMessageMap.putIfAbsent(sender, new LinkedList<>());
                    sendMessageMap.get(sender).add(message);
                    recvMessageMap.putIfAbsent(recver, new LinkedList<>());
                    recvMessageMap.get(recver).add(message);
                }

                // Test code here
//                List<Message> sendMessageList = getMessageListSentBy("user1");
//                List<Message> recvMessageList = getMessageListReceivedBy("user2");
//                for(Message message : sendMessageList){
//                    System.out.println("send message " + message.getSender() + " " + message.getUuid());
//                }
//
//                for(Message message : recvMessageList){
//                    System.out.println("recv message " + message.getReceiver() + " " + message.getUuid());
//                }
//
//                int cntSend = countMessageSentBy("user1");
//                int cntRecv = countMessageReceivedBy("user2");
//                System.out.println("send cnt " + cntSend);
//                System.out.println("recv cnt " + cntRecv);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void insertMessage(Message message) {
        Map<String, Object> map = new HashMap<>();
        map.put(message.getUuid(), gson.toJson(message));
        root.updateChildren(map);
    }

    @Override
    public List<Message> getMessageListSentBy(String username){
        return sendMessageMap.get(username);
    }

    @Override
    public List<Message> getMessageListReceivedBy(String username){
        return recvMessageMap.get(username);
    }

    @Override
    public int countMessageSentBy(String username){
        return sendMessageMap.get(username).size();
    }

    @Override
    public int countMessageReceivedBy(String username){
        return recvMessageMap.get(username).size();
    }
}
