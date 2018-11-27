package cf.flymengo.milionaire_attempt2;

import android.media.Image;

public class User {
    public static int wallet,id;
    public static String name, nickname,rank,email;
    public static Image avatar_img;
    public User() {
        super();
    }
    public User(int ident, String nm, String nk, String rnk, int wall, String mail) //, byte[] img
    {
        id = ident;
        name = nm;
        nickname = nk;
        rank = rnk;
        wallet = wall;
        email = mail;
        //avatar = img;
       /* if (img != null)
        {
            MemoryStream avatar_stream = new MemoryStream(img);
            avatar_img = Image.FromStream(avatar_stream);
        }*/
        avatar_img = null;
    }
}
