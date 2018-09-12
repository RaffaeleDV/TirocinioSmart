<%!public String tutorNome(){
        String tNome = null;
        
        synchronize(session){
            tNome = (String)(((TutorBean)session.getAttribute("Tutor")).getNome());
        }
        
        return tNome;
    }%>
<%!
    public String email(){
        String email = null;
        synchronize(application){
            email = application.getAttribute("email");
        }
        
        return email;
    }
%>
<%!
    public String tel(){
        String tel = null;
        synchronize(application){
            tel = application.getAttribute("tel");
        }
        
        return tel;
    }
%>
<%!
    public String data(){
        GregorianCalendar data = new GregorianCalendar();
        String year = data.get(1);
        String month = data.get(2);
        String day = data.get(3);
        
        return day + "/" + month + "/" + year;      
    }
%>