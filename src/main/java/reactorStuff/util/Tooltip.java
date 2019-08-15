  
  
  
    public static String toSubscript(long no){
        char[] chars=Long.toString(no).toCharArray();
        for(int i=0;i<chars.length;i++){
            chars[i]+=8272;
        }
        return new String(chars);
    }
