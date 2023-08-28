package region_dto;

public class RegionDTO {

	private int region_code;
	private String region_name;

	public RegionDTO() {
	}

	public RegionDTO(int region_code, String region_name) {
		this.region_code = region_code;
		this.region_name = region_name;
	}

	public int getRegion_code() {
		return region_code;
	}

	public void setRegion_code(int region_code) {
		this.region_code = region_code;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	@Override
	public String toString() {
		return region_code + ". " + region_name;
	}

}
