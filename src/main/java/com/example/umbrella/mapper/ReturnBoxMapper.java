package com.example.umbrella.mapper;

import com.example.umbrella.dto.ReturnBoxDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReturnBoxMapper {
    public List<ReturnBoxDto> getAllReturnBox();
    public int countReturnBox(String centercode);
    public void deleteReturnBox(String centercode);
    public List<String> getAllReturnBoxCode(String centercode);
    public void deleteReturnBoxDetail(String lockercode);
    public String maxReturnBoxcode(String centercode);
    public void insertReturnBox(String returnBoxcode, String returnBoxAddr, String centercode);
    public void insertReturnBoxDetail(ReturnBoxDto returnBox);


    public void deleteRentalUmbrellaAppLocker(Map<String,Object> returnUmbMap);
    public void updateReturnUmbrellaStatusApp(Map<String,Object> returnUmbMap);
    public void insertReturnBoxDetailApp(Map<String,Object> returnUmbMap);
    public void updateRentalCntDecreaseApp(Map<String,Object> returnUmbMap);
    public void updateReturnBoxCntApp(Map<String,Object> returnUmbMap);

}
