package com.spring.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.spring.entity.Student;
import com.spring.validator.BirthDate;
import com.spring.validator.NameValid;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {
	@Nullable
	private long id;

//	@NotNull(message = "name must not be null")
//	@Pattern(regexp = "^([aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]\\s*){3,50}$", message = "your name u type have wrong format pls check again, at least 3 characters and max 50")
	@NotNull
	@NameValid
	private String name;

	@NotNull
	@BirthDate
	private String birthday;

	public static StudentDto from(Student student) {
		StudentDto dto = new StudentDto();
		dto.setId(student.getId());
		dto.setName(student.getName());
		dto.setBirthday(new SimpleDateFormat("dd/MM/yyyy").format(student.getBirthday()).toString());
		return dto;
	}

	public static Student from(StudentDto dto) throws ParseException {
		Student s = new Student();
		s.setName(dto.getName());
		s.setBirthday(new Date(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getBirthday()).getTime()));
		return s;

	}
}
