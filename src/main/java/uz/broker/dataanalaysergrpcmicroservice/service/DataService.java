package uz.broker.dataanalaysergrpcmicroservice.service;

import uz.broker.dataanalaysergrpcmicroservice.model.Data;

import java.util.List;

public interface DataService {

    void handle(Data data);

    List<Data> getWithBatch(long batchSize);

}
