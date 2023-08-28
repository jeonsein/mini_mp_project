package region_dao;

import java.util.List;
import region_dto.RegionDTO;

public interface RegionDAOInterface {

	// 지역 리스트 보기
	public List<RegionDTO> regionList();

}
