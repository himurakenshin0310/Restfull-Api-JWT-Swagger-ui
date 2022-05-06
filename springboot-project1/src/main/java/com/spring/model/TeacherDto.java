package com.spring.model;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.spring.entity.Teacher;
import com.spring.validator.BirthDate;
import com.spring.validator.NameValid;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherDto {
	@Nullable
	private long id;

	@NotNull
//	@Pattern(regexp = "^([aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]\\s*){3,50}$", message = "your name u type have wrong format pls check again, at least 3 characters and max 50")
	@NameValid
	private String name;

	@NotNull
//	@Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)", message = "check your date and birthdate have format dd/MM/yyyy")
	@BirthDate
	private String birthDate;

	public static TeacherDto from(Teacher teacher) {
		TeacherDto dto = new TeacherDto();
		dto.setId(teacher.getId());
		dto.setName(teacher.getName());
		dto.setBirthDate(new SimpleDateFormat("dd/MM/YYYY").format(teacher.getBrithdate()).toString());
		return dto;
	}
}
