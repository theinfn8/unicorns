package com.chaostek.unicorns;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chaosburn
 */
public class unicorn
{
    int unicornID;
    String name, gender, description, personality, imagePath;
    
    List<String> interests;
    
    public unicorn()
    {
        unicornID = 0;
        name = "Unicorn";
        gender = "F";
        description = "";
        personality = "";
        
        interests = new ArrayList<>();
        
    }

    public unicorn(int unicornID, String name, String gender, String description, String personality, String imagePath)
    {
        this.unicornID = unicornID;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.personality = personality;
        this.imagePath = imagePath;
        interests = new ArrayList<>();
        
    }
    
    public int getUnicornID() { return unicornID; }
    public void setUnicornID(int unicornID) { this.unicornID = unicornID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPersonality() { return personality; }
    public void setPersonality(String personality) { this.personality = personality; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
    public void addInterest(String interest) { interests.add(interest); }
    
    public String getCard()
    {
        String cardHTML;
        
        cardHTML =
"<div class=\"card\"><img src=\"../images/" + unicornID + "/" + imagePath + "\" alt=\"A Unicorn\" /><div class=\"cardtext\"><h4>" + name + "</h4><p>" + personality + "</p><p><a href=\"/unicorns/unicorn.jsp?id=" + unicornID + "\">Learn more about me!</a></p></div></div>";
        return cardHTML;
    }
}
