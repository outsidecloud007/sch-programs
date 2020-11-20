package com.cht.sch.programs.repository;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import com.cht.sch.programs.model.Program;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProgramListRepository {
	
	private static final String PROGRAM_LIST_KEY = "program-list";
	
	@Autowired
	private RedisTemplate<String, String> StringRedisTemplate;

	@Autowired
	private ObjectMapper opjMapper;
	
	@Autowired
	ResourcePatternResolver resourceResolver;
	
	private ValueOperations<String, String> programListOps;
	
    @PostConstruct
    private void init() {
    	
    	programListOps = StringRedisTemplate.opsForValue();
    	initData();
    } 
	
    public List<Program> list() {
    	    	
    	try {
    		String value = programListOps.get(PROGRAM_LIST_KEY);
			return opjMapper.readValue(value, new TypeReference<List<Program>>() {});
		} catch (IOException err) {
			throw new RuntimeException(err);
		}
    }
    
	public void update(List<Program> programList) {

		try {
			String value = opjMapper.writeValueAsString(programList);
			programListOps.set(PROGRAM_LIST_KEY, value);			
		} catch (Exception err) {
			throw new RuntimeException(err);
		}		
	}

	void initData() {
		programListOps.set(PROGRAM_LIST_KEY, "[ {\r\n" + 
		        "  \"節目\" : \"迪士尼\",\r\n" + 
		        "  \"描述\" : \"主要以2–17歲兒童為主要收視群\",\r\n" + 
		        "  \"內容\" : \"是一個美國有線電視及廣播衛星電視網，為華特迪士尼公司之迪士尼媒體電視網旗下迪士尼ABC電視集團中的迪士尼全球公司擁有。\",\r\n" + 
		        "  \"頻道\" : 5\r\n" + 
		        "}, {\r\n" + 
		        "  \"節目\" : \"動物星球\",\r\n" + 
		        "  \"描述\" : \"全球首屈一指的動物紀實娛樂品牌，有新奇、冒險、歡笑、動人情感、還有生與死，一群愛動物的觀眾，絕對不可以錯過！\",\r\n" + 
		        "  \"內容\" : \"為一家由探索通信（探索頻道、TLC、探索健康頻道及旅遊生活頻道的母公司）出資百分之八十及BBC Worldwide出資百分之二十所創立的動物紀錄片頻道。2005年起由銳迅多媒體獨家代理台灣動物星球影音版權。部分系統業者已啟用高畫質。\",\r\n" + 
		        "  \"頻道\" : 6\r\n" + 
		        "}, {\r\n" + 
		        "  \"節目\" : \"龍兄虎弟\",\r\n" + 
		        "  \"描述\" : \"以張菲、費玉清拿手的說唱與短劇為主\",\r\n" + 
		        "  \"內容\" : \"1987年香港電影《龍兄虎弟》，由成龍、譚詠麟主演。\",\r\n" + 
		        "  \"頻道\" : 18\r\n" + 
		        "}, {\r\n" + 
		        "  \"節目\" : \"GOOD TV\",\r\n" + 
		        "  \"描述\" : \"提供符合聖經真理的優質節目，透過媒體平台傳播上帝大愛在全球華人中間，引領認識耶穌基督救恩並造就門徒成就福音！\",\r\n" + 
		        "  \"內容\" : \"是台灣的第一家和唯一的基督教電視台，也是一間非營利的電視台，1997年建台，1998年9月9日開播，節目總監是寇紹恩牧師，代言人是孫越。\",\r\n" + 
		        "  \"頻道\" : 23\r\n" + 
		        "}, {\r\n" + 
		        "  \"節目\" : \"衛視電影台\",\r\n" + 
		        "  \"描述\" : \"主要播放粵語及華語的電影，大多數播放香港的電影。\",\r\n" + 
		        "  \"內容\" : \"是福斯傳媒集團的一個頻道（2011年12月前為星空傳媒的頻道），也是對亞洲播出的華語電影頻道。本頻道主要播放粵語及華語的電影，大多數播放香港的電影。\",\r\n" + 
		        "  \"頻道\" : 50\r\n" + 
		        "} ]");
		
	}
}
