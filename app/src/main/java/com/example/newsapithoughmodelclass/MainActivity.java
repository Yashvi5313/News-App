package com.example.newsapithoughmodelclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.newsapithoughmodelclass.Adapter.MainFeaturedAdapter;
import com.example.newsapithoughmodelclass.Adapter.MainStoryAdapter;
import com.example.newsapithoughmodelclass.Adapter.MainTeamAdapter;
import com.example.newsapithoughmodelclass.EventModel.EventRoot;
import com.example.newsapithoughmodelclass.FeaturedModel.FeaturedTip;
import com.example.newsapithoughmodelclass.FeaturedModel.Player;
import com.example.newsapithoughmodelclass.FeaturedModel.Root;
import com.example.newsapithoughmodelclass.FeaturedModel.SuggestedTeam;
import com.example.newsapithoughmodelclass.StoryModel.StoryRoot;
import com.example.newsapithoughmodelclass.StoryModel.TopStory;
import com.example.newsapithoughmodelclass.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainFeaturedAdapter mainFeaturedAdapter;
    MainStoryAdapter mainStoryAdapter;
    MainTeamAdapter mainTeamAdapter;
    ActionBarDrawerToggle actionBarDrawerToggle;

    ArrayList<Player> playerArrayList = new ArrayList<>();
    ArrayList<SuggestedTeam> suggestedTeamArrayList = new ArrayList<>();

    String strTipsJson = new String("{\n" + "\"Featured Tips\":[\n" + "{\n" + "\"title\":\"Virat Kohli becomes first player to win 50 international matches in each format\",\n" + "\"image\":\"https://static.toiimg.com/thumb/msid-88117555,imgsize-19356,width-400,resizemode-4/88117555.jpg\",\n" + "\"tips\":[\n" +
            "{\n" + "\"tipstitle\":\"Preview\",\n" + "\"tips\":\"Hobart Hurricanes will lock horns with Sydney Sixers in the 4th match of the BBL 2021/22 on Wednesday.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Match Details\",\n" + "\"tips\":\"Hobart Hurricanes failed to qualify for the knockouts in the previous edition as they finished sixth in the standings with 28 points.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.1 ...\",\n" + "\"tips\":\"They need to be on top of their game as they are facing Sydney Sixers who are a formidable unit.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.2 ...\",\n" + "\"tips\":\"Jimmy Peirson (vc), Chris Lynn, Alex Hales, Alex Ross, Max Bryant, Ben Cutting (c), Jack Wildermuth, Chris Green, Gurinder Sandhu, Mujeeb ur Rahman, Tanveer Sangha\"\n" + "}\n" + "],\n" + "\"time\":\"Dec 6, 2021 11:45 am\"\n" + "},\n" +
            "{\n" + "\"title\":\"India vs New Zealand, 2nd Test: Ajaz Patel emulates Jim Laker and Anil Kumble, takes all 10 wickets in an innings\",\n" + "\"image\":\"https://static.toiimg.com/thumb/msid-88087992,imgsize-38990,width-400,resizemode-4/88087992.jpg\",\n" + "\"tips\":[\n" +
            "{\n" + "\"tipstitle\":\"Preview\",\n" + "\"tips\":\"Hobart Hurricanes will lock horns with Sydney Sixers in the 4th match of the BBL 2021/22 on Wednesday.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Match Details\",\n" + "\"tips\":\"Hobart Hurricanes failed to qualify for the knockouts in the previous edition as they finished sixth in the standings with 28 points.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.1 ...\",\n" + "\"tips\":\"Jimmy Peirson, Sam Billings, Tom Cooper, Chris Lynn (vc), Alex Hales (c), Ben Cutting, Jack Wildermuth, Daniel Sams, Matt Kuhnemann, Mujeeb ur Rahman, Tanveer Sangha\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.2 ...\",\n" + "\"tips\":\"Jimmy Peirson (vc), Chris Lynn, Alex Hales, Alex Ross, Max Bryant, Ben Cutting (c), Jack Wildermuth, Chris Green, Gurinder Sandhu, Mujeeb ur Rahman, Tanveer Sangha\"\n" + "}\n" + "],\n" + "\"time\":\"Dec 4, 2021 6:09 pm\"\n" + "},\n" +
            "{\n" + "\"title\":\"India vs New Zealand: Out or not out? Virat Kohli's controversial dismissal is a tough one to call\",\n" + "\"image\":\"https://static.toiimg.com/thumb/msid-88074897,imgsize-22236,width-400,resizemode-4/88074897.jpg\",\n" + "\"tips\":[\n" + "{\n" + "\"tipstitle\":\"Preview\",\n" + "\"tips\":\"Hobart Hurricanes will lock horns with Sydney Sixers in the 4th match of the BBL 2021/22 on Wednesday.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Match Details\",\n" + "\"tips\":\"Hobart Hurricanes failed to qualify for the knockouts in the previous edition as they finished sixth in the standings with 28 points.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.1 ...\",\n" + "\"tips\":\"They need to be on top of their game as they are facing Sydney Sixers who are a formidable unit.\"\n" + "},\n" + "{\n" + "\"tipstitle\":\"Suggested Playing XI No.2 ...\",\n" + "\"tips\":\"Jimmy Peirson (vc), Chris Lynn, Alex Hales, Alex Ross, Max Bryant, Ben Cutting (c), Jack Wildermuth, Chris Green, Gurinder Sandhu, Mujeeb ur Rahman, Tanveer Sangha\"\n" + "}\n" + "],\n" + "\"time\":\"Dec 3, 2021 8:04 pm\"\n" + "},\n" +
            "{\n" + "\"title\":\"2nd Test: India beat New Zealand by 372 runs for their biggest ever victory\",\n" + "\"image\":\"https://static.toiimg.com/thumb/msid-88135064,imgsize-139150,width-400,resizemode-4/88135064.jpg\",\n" + "\"tips\":[\n" + "{\n" + "\"tipstitle\":\"Preview\",\n" + "\"tips\":\"Hobart Hurricanes will lock horns with Sydney Sixers in the 4th match of the BBL 2021/22 on Wednesday.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Match Details\",\n" + "\"tips\":\"Hobart Hurricanes failed to qualify for the knockouts in the previous edition as they finished sixth in the standings with 28 points.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.1 ...\",\n" + "\"tips\":\"Jimmy Peirson, Sam Billings, Tom Cooper, Chris Lynn (vc), Alex Hales (c), Ben Cutting, Jack Wildermuth, Daniel Sams, Matt Kuhnemann, Mujeeb ur Rahman, Tanveer Sangha\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.2 ...\",\n" + "\"tips\":\"Jimmy Peirson (vc), Chris Lynn, Alex Hales, Alex Ross, Max Bryant, Ben Cutting (c), Jack Wildermuth, Chris Green, Gurinder Sandhu, Mujeeb ur Rahman, Tanveer Sangha\"\n" + "}\n" + "],\n" + "\"time\":\"Dec 7, 2021 8:49 am\"\n" + "},\n" +
            "{\n" + "\"title\":\"In 6 points: Key highlights of India vs New Zealand second Test\",\n" + "\"image\":\"https://static.toiimg.com/thumb/msid-88116307,imgsize-86784,width-400,resizemode-4/88116307.jpg\",\n" + "\"tips\":[\n" + "{\n" + "\"tipstitle\":\"Preview\",\n" + "\"tips\":\"Hobart Hurricanes will lock horns with Sydney Sixers in the 4th match of the BBL 2021/22 on Wednesday.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Match Details\",\n" + "\"tips\":\"Hobart Hurricanes failed to qualify for the knockouts in the previous edition as they finished sixth in the standings with 28 points.\"\n" + "},\n" + "{\n" + "\"tipstitle\":\"Suggested Playing XI No.1 ...\",\n" + "\"tips\":\"They need to be on top of their game as they are facing Sydney Sixers who are a formidable unit.\"\n" + "},\n" +
            "{\n" + "\"tipstitle\":\"Suggested Playing XI No.2 ...\",\n" + "\"tips\":\"Jimmy Peirson (vc), Chris Lynn, Alex Hales, Alex Ross, Max Bryant, Ben Cutting (c), Jack Wildermuth, Chris Green, Gurinder Sandhu, Mujeeb ur Rahman, Tanveer Sangha\"\n" + "}\n" + "],\n" + "\"time\":\"Dec 6, 2021 7:22 pm\"\n" + "}\n" + "]\n" + "}");


    String strStoryJson = new String("{\n" + "\"Top Stories\":[\n" + "{\n" + "\"title\":\"US: 5 dead, more than 40 injured after vehicle plows through parade in Wisconsin\",\n" +
            "\"sub title\":\"WAUKESHA: Five people were killed and more than 40 injured after an SUV sped through a Christmas parade in Waukesha, Wisconsin, on Sunday\",\n" +
            "\"full story\":\"WAUKESHA: Five people were killed and more than 40 injured after an SUV sped through a Christmas parade in Waukesha, Wisconsin, on Sunday, knocking down dozens of people including youngsters waving pompoms and a group of Dancing  Grannies At this time, we can confirm that five people are deceased and over 40 are injured, the Waukesha Police Department said on its Facebook page. However, these numbers may change as we collect additional information. The SUV came by at full speed,said Santamaria. Then I started to hear people screaming.\",\n" +
            "\"image\":\"https://static.toiimg.com/thumb/msid-87838889,imgsize-29076,width-400,resizemode-4/87838889.jpg\",\n" + "\"time\":\"10:25 pm\"\n" + "},\n" +
            "{\n" + "\"title\":\"Airtel raises tariffs, others could follow\",\n" + "\"sub title\":\"NEW DELHI: Your mobile tariffs are set to go up.\",\n" +
            "\"full story\":\"NEW DELHI: Your mobile tariffs are set to go up. Bharti Airtel, one of the country's oldest mobile operator, will be increasing tariffs for pre-paid customers by up 20-25% from November 26, signalling hardening of tariffs as companies look at ways to shore up profitability ahead of new investments in 5G and telecom network. Tariffs for post-paid customers may also go up further after some changes mad recently. Airtel said that the move will help it raise mobile Average Revenue Per User (ARPU) – a key yardstick to measure profitability of mobile companies- to Rs 200 and ultimately at Rs 300 so as to provide a reasonable return on capital that allows for a financially healthy business model.\",\n" +
            "\"image\":\"https://static.toiimg.com/thumb/msid-87840191,imgsize-299900,width-400,resizemode-4/87840191.jpg\",\n" + "\"time\":\"08:49 am\"\n" + "},\n" +
            "{\n" + "\"title\":\"India vs New Zealand: Good series win but we are also quite realistic, says Rahul Dravid\",\n" + "\"sub title\":\"KOLKATA: Newly-appointed India chief coach Rahul Dravid says\",\n" +
            "\"full story\":\"KOLKATA: Newly-appointed India chief coach Rahul Dravid says the 3-0 result over New Zealand augurs well for the team in the long-run but at the same time, he is realistic enough to not read too much into the overall outcome with Black Caps having to play the series three days after the T20 World Cup final. Some of the key players from both teams were rested for the series as part of workload management. It was a really good series win. Everyone played really well right through the series. It feels good, nice to start well,” Dravid said at the post-match presentation here.\",\n" +
            "\"image\":\"https://static.toiimg.com/thumb/msid-87840020,imgsize-35624,width-400,resizemode-4/87840020.jpg\",\n" + "\"time\":\"09:16 pm\"\n" + "},\n" +
            "{\n" + "\"title\":\"Andhra Pradesh flood situation grim as toll rises to 41; Tirupati cut off\",\n" + "\"sub title\":\"TIRUPATI The flood situation in Andhra Pradesh continued to be grim with arterial roads cut off even as the toll increased to 41 with 12 more deaths reported on Sunday.\",\n" +
            "\"full story\":\"TIRUPATI The flood situation in Andhra Pradesh continued to be grim with arterial roads cut off even as the toll increased to 41 with 12 more deaths reported on Sunday. Train and bus services remained affected while many villages and towns in Rayalaseema and Nellore district remained under water Streams rivulets and village tanks were full to the brim and a few of them were overflowing causing concern to people living in lowlying areas. Rail and road connectivity to the temple city of Tirupati remained cut off with the closure of KadapaTirupati highway NelloreTirupati highway and BengaluruTirupati highway.\",\n" +
            "\"image\":\"https://static.toiimg.com/thumb/msid-87838022,imgsize-88514,width-400,resizemode-4/87838022.jpg\",\n" + "\"time\":\"03:25 am\"\n" + "},\n" +
            "{\n" + "\"title\":\"Rakul Preet Singh spills the beans on her wedding plans with beau Jackky Bhagnani\",\n" + "\"sub title\":\"Rakul Preet Singh confessed to being in a romantic relationship with actor-producer Jackky Bhagnani in a social media post on her birthday a few months ago.\",\n" +
            "\"full story\":\"Rakul Preet Singh confessed to being in a romantic relationship with actor-producer Jackky Bhagnani in a social media post on her birthday a few months ago. In a new interview with a news portal, the actress was asked about her marriage plans. Replying to the same, she reportedly said that whenever that will happen, she will share it like any other thing. However, right now, she is focusing on her career because that’s exactly what she is in the industry for. The actress, who often keeps her personal matters private, told the news portal that she chooses to listen to things that she wants to listen to and she chooses not to get affected by things.\",\n" +
            "\"image\":\"https://static.toiimg.com/photo/msid-87841077/87841077.jpg\",\n" + "\"time\":\"10:08 am\"\n" + "}\n" + "]\n" + "}");


    String strJson = new String("{\n" +
            "\"ci_rslt\":{\n" +
            "\"mch\":[\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5436\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/13/2021:08:15\",\n" +
            "\"local_dt\":\"12/13/2021:19:15\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"11\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/13/2021:08:15\",\n" +
            "\"end_dt\":\"12/13/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"BB League 2021/22\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"BBL\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"525\",\n" +
            "\"ikn\":\"events/logo_images/000/000/525/big/bb-leagueMens21-22.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"01/28/2022:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"Carrara Oval\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1985\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/985/big/CarraraOvalQueensland.png\",\n" +
            "\"city\":\"Queensland\",\n" +
            "\"ctry\":\"Australia\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Brisb Ht\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Brisb Ht\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/138/thumb/BH.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/138/original/BH.png\",\n" +
            "\"dnm\":\"BrisbHt\",\n" +
            "\"evtmid\":\"1645\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Melbourne R\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Melbourne R\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/190/thumb/MR.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/190/original/MR.png\",\n" +
            "\"dnm\":\"MelbReneg\",\n" +
            "\"evtmid\":\"1648\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5396\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/13/2021:13:00\",\n" +
            "\"local_dt\":\"12/13/2021:18:00\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20I\",\n" +
            "\"ord\":\"1\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/13/2021:13:00\",\n" +
            "\"end_dt\":\"12/13/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"West Indies vs Pakistan\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"PAK vs WI\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"523\",\n" +
            "\"ikn\":\"events/logo_images/000/000/523/big/wvsp.png\",\n" +
            "\"cat\":\"series\",\n" +
            "\"sdt\":\"12/13/2021:00:00\",\n" +
            "\"edt\":\"12/22/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"National Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1974\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/974/big/NationalStadiumKarachi.png\",\n" +
            "\"city\":\"Karachi\",\n" +
            "\"ctry\":\"Pakistan\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Pakistan\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Pakistan\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/004/thumb/pakistan-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/004/original/pakistan-flg.png\",\n" +
            "\"dnm\":\"PAK\",\n" +
            "\"evtmid\":\"1638\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"West Indies\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"West Indies\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/009/thumb/westindies-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/009/original/westindies-flg.png\",\n" +
            "\"dnm\":\"WI\",\n" +
            "\"evtmid\":\"1639\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5415\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/13/2021:14:00\",\n" +
            "\"local_dt\":\"12/13/2021:19:30\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"14\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/13/2021:14:00\",\n" +
            "\"end_dt\":\"12/13/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"Lanka PL 2021\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"LP League\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"524\",\n" +
            "\"ikn\":\"events/logo_images/000/000/524/big/lp-league2021.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"12/23/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"R.Premadasa Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1975\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/975/big/RPremadasastadium-KhettaramaStadium.png\",\n" +
            "\"city\":\"Colombo\",\n" +
            "\"ctry\":\"Sri Lanka\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Galle Gldtrs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Galle Gldtrs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/240/thumb/GalleGldtrs.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/240/original/GalleGldtrs.png\",\n" +
            "\"dnm\":\"GGLDTRS\",\n" +
            "\"evtmid\":\"1640\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Dambulla Gnts\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Dambulla Gnts\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/241/thumb/DambullaGiants_DG.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/241/original/DambullaGiants_DG.png\",\n" +
            "\"dnm\":\"DGNTS\",\n" +
            "\"evtmid\":\"1642\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5437\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/14/2021:08:15\",\n" +
            "\"local_dt\":\"12/14/2021:19:15\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"12\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/14/2021:08:15\",\n" +
            "\"end_dt\":\"12/14/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"BB League 2021/22\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"BBL\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"525\",\n" +
            "\"ikn\":\"events/logo_images/000/000/525/big/bb-leagueMens21-22.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"01/28/2022:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"Bellerive Oval\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1986\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/986/big/BelleriveOval-BlundstoneArena-hobart.png\",\n" +
            "\"city\":\"Hobart\",\n" +
            "\"ctry\":\"Australia\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Hobart Hur\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Hobart Hur\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/144/thumb/HH.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/144/original/HH.png\",\n" +
            "\"dnm\":\"HobHur\",\n" +
            "\"evtmid\":\"1647\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Perth Scor\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Perth Scor\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/130/thumb/PS.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/130/original/PS.png\",\n" +
            "\"dnm\":\"PerthSco\",\n" +
            "\"evtmid\":\"1650\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5416\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/14/2021:09:30\",\n" +
            "\"local_dt\":\"12/14/2021:15:00\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"15\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/14/2021:09:30\",\n" +
            "\"end_dt\":\"12/14/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"Lanka PL 2021\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"LP League\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"524\",\n" +
            "\"ikn\":\"events/logo_images/000/000/524/big/lp-league2021.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"12/23/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"R.Premadasa Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1975\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/975/big/RPremadasastadium-KhettaramaStadium.png\",\n" +
            "\"city\":\"Colombo\",\n" +
            "\"ctry\":\"Sri Lanka\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Kandy Warrs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Kandy Warrs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/238/thumb/KandyWarrs_KW.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/238/original/KandyWarrs_KW.png\",\n" +
            "\"dnm\":\"KWARRS\",\n" +
            "\"evtmid\":\"1643\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Colombo Strs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Colombo Strs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/237/thumb/ColomboStars_CS.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/237/original/ColomboStars_CS.png\",\n" +
            "\"dnm\":\"CSTRS\",\n" +
            "\"evtmid\":\"1644\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5397\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/14/2021:13:00\",\n" +
            "\"local_dt\":\"12/14/2021:18:00\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20I\",\n" +
            "\"ord\":\"2\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/14/2021:13:00\",\n" +
            "\"end_dt\":\"12/14/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"West Indies vs Pakistan\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"PAK vs WI\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"523\",\n" +
            "\"ikn\":\"events/logo_images/000/000/523/big/wvsp.png\",\n" +
            "\"cat\":\"series\",\n" +
            "\"sdt\":\"12/13/2021:00:00\",\n" +
            "\"edt\":\"12/22/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"National Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1974\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/974/big/NationalStadiumKarachi.png\",\n" +
            "\"city\":\"Karachi\",\n" +
            "\"ctry\":\"Pakistan\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Pakistan\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Pakistan\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/004/thumb/pakistan-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/004/original/pakistan-flg.png\",\n" +
            "\"dnm\":\"PAK\",\n" +
            "\"evtmid\":\"1638\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"West Indies\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"West Indies\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/009/thumb/westindies-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/009/original/westindies-flg.png\",\n" +
            "\"dnm\":\"WI\",\n" +
            "\"evtmid\":\"1639\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5417\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/14/2021:14:00\",\n" +
            "\"local_dt\":\"12/14/2021:19:30\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"16\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/14/2021:14:00\",\n" +
            "\"end_dt\":\"12/14/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"Lanka PL 2021\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"LP League\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"524\",\n" +
            "\"ikn\":\"events/logo_images/000/000/524/big/lp-league2021.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"12/23/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"R.Premadasa Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1975\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/975/big/RPremadasastadium-KhettaramaStadium.png\",\n" +
            "\"city\":\"Colombo\",\n" +
            "\"ctry\":\"Sri Lanka\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Dambulla Gnts\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Dambulla Gnts\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/241/thumb/DambullaGiants_DG.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/241/original/DambullaGiants_DG.png\",\n" +
            "\"dnm\":\"DGNTS\",\n" +
            "\"evtmid\":\"1642\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Jaffna Kngs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Jaffna Kngs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/239/thumb/JaffnaKings_JK.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/239/original/JaffnaKings_JK.png\",\n" +
            "\"dnm\":\"JKNGS\",\n" +
            "\"evtmid\":\"1641\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5438\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/15/2021:08:15\",\n" +
            "\"local_dt\":\"12/15/2021:19:15\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"13\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/15/2021:08:15\",\n" +
            "\"end_dt\":\"12/15/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"BB League 2021/22\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"BBL\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"525\",\n" +
            "\"ikn\":\"events/logo_images/000/000/525/big/bb-leagueMens21-22.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"01/28/2022:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"Melbourne Cricket Ground\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1983\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/983/big/MelbourneCricketGround.png\",\n" +
            "\"city\":\"Melbourne\",\n" +
            "\"ctry\":\"Australia\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Melbourne Str\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Melbourne Str\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/191/thumb/MS.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/191/original/MS.png\",\n" +
            "\"dnm\":\"MelbStars\",\n" +
            "\"evtmid\":\"1649\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Sydney 6\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Sydney 6\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/131/thumb/SS.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/131/original/SS.png\",\n" +
            "\"dnm\":\"SydSix\",\n" +
            "\"evtmid\":\"1651\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5377\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/16/2021:04:00\",\n" +
            "\"local_dt\":\"12/16/2021:14:30\",\n" +
            "\"rxo\":\"450\",\n" +
            "\"mxo\":\"450\",\n" +
            "\"typ\":\"Test\",\n" +
            "\"ord\":\"2\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/16/2021:04:00\",\n" +
            "\"end_dt\":\"12/20/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"The Ashes 2021/22\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"ASHES\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"520\",\n" +
            "\"ikn\":\"events/logo_images/000/000/520/big/ashes2021.png\",\n" +
            "\"cat\":\"series\",\n" +
            "\"sdt\":\"12/08/2021:00:00\",\n" +
            "\"edt\":\"01/18/2022:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"Adelaide Oval\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1965\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/965/big/AdelaideOval.png\",\n" +
            "\"city\":\"Adelaide\",\n" +
            "\"ctry\":\"Australia\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Australia\",\n" +
            "\"$\":{\n" +
            "\"tname\":\"Australia\",\n" +
            "\"ach\":\"-1\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/007/thumb/australia-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/007/original/australia-flg.png\",\n" +
            "\"dnm\":\"AUS\",\n" +
            "\"evtmid\":\"1632\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "},\n" +
            "\"ain1\":[\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"rns\":\"-1\",\n" +
            "\"fo\":\"0\",\n" +
            "\"bat\":\"no\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"ord\":\"-1\",\n" +
            "\"dcl\":\"0\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"ain2\":[\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"fo\":\"0\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"ord\":\"-1\",\n" +
            "\"dcl\":\"0\"\n" +
            "}\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"England\",\n" +
            "\"$\":{\n" +
            "\"tname\":\"England\",\n" +
            "\"ach\":\"-1\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/005/thumb/england-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/005/original/england-flg.png\",\n" +
            "\"dnm\":\"ENG\",\n" +
            "\"evtmid\":\"1633\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "},\n" +
            "\"bin1\":[\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"rns\":\"-1\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"bat\":\"no\",\n" +
            "\"fo\":\"0\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"ord\":\"-1\",\n" +
            "\"dcl\":\"0\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"bin2\":[\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"rns\":\"-1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"ord\":\"-1\",\n" +
            "\"dcl\":\"0\",\n" +
            "\"fo\":\"0\",\n" +
            "\"ists\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5418\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/16/2021:09:30\",\n" +
            "\"local_dt\":\"12/16/2021:15:00\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"17\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/16/2021:09:30\",\n" +
            "\"end_dt\":\"12/16/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"Lanka PL 2021\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"LP League\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"524\",\n" +
            "\"ikn\":\"events/logo_images/000/000/524/big/lp-league2021.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"12/23/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"R.Premadasa Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1975\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/975/big/RPremadasastadium-KhettaramaStadium.png\",\n" +
            "\"city\":\"Colombo\",\n" +
            "\"ctry\":\"Sri Lanka\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Colombo Strs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Colombo Strs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/237/thumb/ColomboStars_CS.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/237/original/ColomboStars_CS.png\",\n" +
            "\"dnm\":\"CSTRS\",\n" +
            "\"evtmid\":\"1644\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Jaffna Kngs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Jaffna Kngs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/239/thumb/JaffnaKings_JK.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/239/original/JaffnaKings_JK.png\",\n" +
            "\"dnm\":\"JKNGS\",\n" +
            "\"evtmid\":\"1641\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5398\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/16/2021:13:00\",\n" +
            "\"local_dt\":\"12/16/2021:18:00\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20I\",\n" +
            "\"ord\":\"3\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/16/2021:13:00\",\n" +
            "\"end_dt\":\"12/16/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"West Indies vs Pakistan\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"PAK vs WI\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"523\",\n" +
            "\"ikn\":\"events/logo_images/000/000/523/big/wvsp.png\",\n" +
            "\"cat\":\"series\",\n" +
            "\"sdt\":\"12/13/2021:00:00\",\n" +
            "\"edt\":\"12/22/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"National Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1974\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/974/big/NationalStadiumKarachi.png\",\n" +
            "\"city\":\"Karachi\",\n" +
            "\"ctry\":\"Pakistan\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Pakistan\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Pakistan\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/004/thumb/pakistan-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/004/original/pakistan-flg.png\",\n" +
            "\"dnm\":\"PAK\",\n" +
            "\"evtmid\":\"1638\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"West Indies\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"West Indies\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/009/thumb/westindies-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/009/original/westindies-flg.png\",\n" +
            "\"dnm\":\"WI\",\n" +
            "\"evtmid\":\"1639\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5419\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/16/2021:14:00\",\n" +
            "\"local_dt\":\"12/16/2021:19:30\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"18\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/16/2021:14:00\",\n" +
            "\"end_dt\":\"12/16/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"Lanka PL 2021\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"LP League\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"524\",\n" +
            "\"ikn\":\"events/logo_images/000/000/524/big/lp-league2021.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"12/23/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"R.Premadasa Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1975\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/975/big/RPremadasastadium-KhettaramaStadium.png\",\n" +
            "\"city\":\"Colombo\",\n" +
            "\"ctry\":\"Sri Lanka\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Kandy Warrs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Kandy Warrs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/238/thumb/KandyWarrs_KW.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/238/original/KandyWarrs_KW.png\",\n" +
            "\"dnm\":\"KWARRS\",\n" +
            "\"evtmid\":\"1643\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Dambulla Gnts\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Dambulla Gnts\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/241/thumb/DambullaGiants_DG.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/241/original/DambullaGiants_DG.png\",\n" +
            "\"dnm\":\"DGNTS\",\n" +
            "\"evtmid\":\"1642\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5420\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/17/2021:09:30\",\n" +
            "\"local_dt\":\"12/17/2021:15:00\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"19\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/17/2021:09:30\",\n" +
            "\"end_dt\":\"12/17/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"Lanka PL 2021\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"LP League\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"524\",\n" +
            "\"ikn\":\"events/logo_images/000/000/524/big/lp-league2021.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"12/23/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"R.Premadasa Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1975\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/975/big/RPremadasastadium-KhettaramaStadium.png\",\n" +
            "\"city\":\"Colombo\",\n" +
            "\"ctry\":\"Sri Lanka\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Jaffna Kngs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Jaffna Kngs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/239/thumb/JaffnaKings_JK.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/239/original/JaffnaKings_JK.png\",\n" +
            "\"dnm\":\"JKNGS\",\n" +
            "\"evtmid\":\"1641\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Galle Gldtrs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Galle Gldtrs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/240/thumb/GalleGldtrs.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/240/original/GalleGldtrs.png\",\n" +
            "\"dnm\":\"GGLDTRS\",\n" +
            "\"evtmid\":\"1640\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5421\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/17/2021:14:00\",\n" +
            "\"local_dt\":\"12/17/2021:19:30\",\n" +
            "\"rxo\":\"20\",\n" +
            "\"mxo\":\"20\",\n" +
            "\"typ\":\"T20\",\n" +
            "\"ord\":\"20\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/17/2021:14:00\",\n" +
            "\"end_dt\":\"12/17/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"Lanka PL 2021\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"LP League\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"524\",\n" +
            "\"ikn\":\"events/logo_images/000/000/524/big/lp-league2021.png\",\n" +
            "\"cat\":\"tournament\",\n" +
            "\"sdt\":\"12/05/2021:00:00\",\n" +
            "\"edt\":\"12/23/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"R.Premadasa Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1975\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/975/big/RPremadasastadium-KhettaramaStadium.png\",\n" +
            "\"city\":\"Colombo\",\n" +
            "\"ctry\":\"Sri Lanka\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Colombo Strs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Colombo Strs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/237/thumb/ColomboStars_CS.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/237/original/ColomboStars_CS.png\",\n" +
            "\"dnm\":\"CSTRS\",\n" +
            "\"evtmid\":\"1644\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"Kandy Warrs\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Kandy Warrs\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/238/thumb/KandyWarrs_KW.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/238/original/KandyWarrs_KW.png\",\n" +
            "\"dnm\":\"KWARRS\",\n" +
            "\"evtmid\":\"1643\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"$\":{\n" +
            "\"id\":\"5399\",\n" +
            "\"curr_sts\":\"normal_play\",\n" +
            "\"gmt_dt\":\"12/18/2021:08:00\",\n" +
            "\"local_dt\":\"12/18/2021:13:00\",\n" +
            "\"rxo\":\"50\",\n" +
            "\"mxo\":\"50\",\n" +
            "\"typ\":\"ODI\",\n" +
            "\"ord\":\"1\",\n" +
            "\"lvl\":\"Group\",\n" +
            "\"sts\":\"futr\",\n" +
            "\"dt\":\"12/18/2021:08:00\",\n" +
            "\"end_dt\":\"12/18/2021:00:00\",\n" +
            "\"tday\":\"\",\n" +
            "\"tssn\":\"\"\n" +
            "},\n" +
            "\"evt\":[\n" +
            "{\n" +
            "\"_\":\"West Indies vs Pakistan\",\n" +
            "\"$\":{\n" +
            "\"cd\":\"PAK vs WI\",\n" +
            "\"lvl\":\"n.a\",\n" +
            "\"sts\":\"live\",\n" +
            "\"id\":\"523\",\n" +
            "\"ikn\":\"events/logo_images/000/000/523/big/wvsp.png\",\n" +
            "\"cat\":\"series\",\n" +
            "\"sdt\":\"12/13/2021:00:00\",\n" +
            "\"edt\":\"12/22/2021:00:00\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"vnu\":[\n" +
            "{\n" +
            "\"_\":\"National Stadium\",\n" +
            "\"$\":{\n" +
            "\"id\":\"1974\",\n" +
            "\"lat\":\"-1\",\n" +
            "\"lon\":\"-1\",\n" +
            "\"ikn\":\"venues/venue_images/000/001/974/big/NationalStadiumKarachi.png\",\n" +
            "\"city\":\"Karachi\",\n" +
            "\"ctry\":\"Pakistan\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tma\":[\n" +
            "{\n" +
            "\"_\":\"Pakistan\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"Pakistan\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/004/thumb/pakistan-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/004/original/pakistan-flg.png\",\n" +
            "\"dnm\":\"PAK\",\n" +
            "\"evtmid\":\"1638\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"tmb\":[\n" +
            "{\n" +
            "\"_\":\"West Indies\",\n" +
            "\"$\":{\n" +
            "\"ach\":\"-1\",\n" +
            "\"tname\":\"West Indies\",\n" +
            "\"ists\":\"n.a\",\n" +
            "\"ikn\":\"teams/flag_images/000/000/009/thumb/westindies-flg.png\",\n" +
            "\"big_ikn\":\"teams/flag_images/000/000/009/original/westindies-flg.png\",\n" +
            "\"dnm\":\"WI\",\n" +
            "\"evtmid\":\"1639\",\n" +
            "\"rns\":\"-1\",\n" +
            "\"wkt\":\"-1\",\n" +
            "\"ovr\":\"-1.1\",\n" +
            "\"rr\":\"-1.1\",\n" +
            "\"bat\":\"n.a\",\n" +
            "\"tos\":\"n.a\",\n" +
            "\"won\":\"n.a\"\n" +
            "}\n" +
            "}\n" +
            "],\n" +
            "\"nts\":[\n" +
            "\"n.a\"\n" +
            "]\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.myDrawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close);
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_account:
                        break;
                    case R.id.nav_settings:
                        break;
                    case R.id.nav_logout:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Do you want to exit ?");
                        builder.setTitle("Alert !");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;
                }
                binding.myDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        actionBarDrawerToggle.syncState();


        Gson gson = new Gson();
        Root root = gson.fromJson(strTipsJson, Root.class);
        StoryRoot storyRoot = gson.fromJson(strStoryJson, StoryRoot.class);

        EventRoot eventRoot = gson.fromJson(strJson, EventRoot.class);

        mainFeaturedAdapter = new MainFeaturedAdapter(MainActivity.this, root.getFeaturedTips());
        binding.pager1.setAdapter(mainFeaturedAdapter);

        TabLayoutMediator tabLayout = new TabLayoutMediator(binding.tabPool, binding.pager1, true, (tab, position) -> {
        });
        tabLayout.attach();

        binding.ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String tipsJson = gson.toJson(root.getFeaturedTips());
                Intent intent = new Intent(MainActivity.this, FeaturedTipsActivity.class);
                intent.putExtra("Tips", tipsJson);
                startActivity(intent);
            }
        });

        mainFeaturedAdapter.setItemClickListener(new MainFeaturedAdapter.TipsterClickListener() {
            @Override
            public void onItemClick(FeaturedTip featuredTips, int pos) {
                Gson gson2 = new Gson();
                String tipJson = gson2.toJson(featuredTips.getTips());
                Intent intent1 = new Intent(MainActivity.this, FanTipsActivity.class);
                intent1.putExtra("TitleTips", tipJson);
                startActivity(intent1);
            }
        });


        /**********************************************************************/
        mainStoryAdapter = new MainStoryAdapter(MainActivity.this, storyRoot.getTopStories());
        binding.pager2.setAdapter(mainStoryAdapter);

        TabLayoutMediator tabLayout1 = new TabLayoutMediator(binding.tabPool1, binding.pager2, true, (tab, position) -> {
        });
        tabLayout1.attach();

        binding.ViewAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson1 = new Gson();
                String StoryJson = gson1.toJson(storyRoot.getTopStories());
                Intent intent = new Intent(MainActivity.this, TopStoryActivity.class);
                intent.putExtra("Story", StoryJson);
                startActivity(intent);
            }
        });

        mainStoryAdapter.setItemClickListener(new MainStoryAdapter.ItemClickListener() {
            @Override
            public void onItemClick(TopStory topStory, int pos) {
                Gson gson1 = new Gson();
                String storyJson = gson1.toJson(topStory);
                Intent intent1 = new Intent(MainActivity.this, FullStoryActivity.class);
                intent1.putExtra("FullStory", storyJson);
                startActivity(intent1);
            }
        });

        /**********************************************************************/

        mainTeamAdapter = new MainTeamAdapter(MainActivity.this, eventRoot.ci_rslt.mch);
        binding.pager3.setAdapter(mainTeamAdapter);

        TabLayoutMediator tabLayout2 = new TabLayoutMediator(binding.tabPool2, binding.pager3, true, (tab, position) -> {
        });
        tabLayout2.attach();

        binding.ViewAll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson1 = new Gson();
                String TeamJson = gson1.toJson(eventRoot.ci_rslt.mch);
                Intent intent = new Intent(MainActivity.this, UpcomingEventActivity.class);
                intent.putExtra("Team", TeamJson);
                startActivity(intent);
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification_background:
                Toast.makeText(getApplicationContext(), "You Have one Notification...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.LogIn:
                Intent intent = new Intent(MainActivity.this, LoginMainActivity.class);
                startActivity(intent);
                break;
            case R.id.LogOut:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to exit ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
}
