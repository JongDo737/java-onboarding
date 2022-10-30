package onboarding;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

class Problem6 {
    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = new ArrayList<>();
        System.out.println(substrb(forms.get(0).get(1),1,6,3));
        
//        }
        return answer;
    }


    // 한글 추출 메서드
    // input : 문자열 sIndex : 추출 시작 인덱스  byteSize : 추출 바이트 길이 byteLenKor : 한글 한글자 바이트수
    public static String substrb(String input, int sIndex, int byteSize, int byteLenKor) {
        if (input == null) return null;
        if (input.length() == 0 ) return input;
        if (sIndex < 0 || byteSize < 0 || byteLenKor > 3) return input;
        int iLen = 0;
        int iPrevSumBytes = 0;
        int iOneWordBytes = 0;

        do {
            iPrevSumBytes += iOneWordBytes;
            iOneWordBytes = (Character.getType(input.charAt(sIndex + iLen)) == Character.OTHER_LETTER) ? byteLenKor : 1;

            if (iPrevSumBytes + iOneWordBytes <= byteSize) {
                ++iLen;
            } else {
                break;
            }
        } while(sIndex + iLen < input.length());

        String strFmt = null;
        try {
            int iOutBytes = (input.substring(sIndex, sIndex + iLen)).getBytes("UTF-8").length;
            strFmt = (byteSize - iOutBytes > 0) ? "%-"+(byteSize - iOutBytes)+"s":"%s";
        } catch(UnsupportedEncodingException e) {
            return null;
        }

        return input.substring(sIndex, sIndex + iLen) + String.format(strFmt,"");
    }
    // 어떤 글자가 중복되었는지 확인 후 포함된 index를 포함한 List 반환
    public static List<Integer> isOverlapped(List<List<String>> forms, String name) {

        List<Integer> indexList = new ArrayList<>();
        for(int i=0; i<forms.size(); i++) {
            if(forms.get(i).get(1).contains(name)) {        // 글자가 포함되어있다면
                indexList.add(i);
            }
        }
        return indexList;
    }
}

