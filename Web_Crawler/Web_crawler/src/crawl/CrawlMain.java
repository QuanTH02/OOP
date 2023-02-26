package crawl;

import java.io.IOException;

public class CrawlMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		DiTichCrawl diTich = new DiTichCrawl();
		diTich.crawl();
		LeHoiCrawl leHoi = new LeHoiCrawl();
		leHoi.crawl();
		SuKienCrawl suKien = new SuKienCrawl();
		suKien.crawl();
		
		TrangNguyenCrawl trangNguyen = new TrangNguyenCrawl();
		trangNguyen.crawl();
		
		TrieuDaiCrawl trieuDai = new TrieuDaiCrawl();
		trieuDai.crawl();
		VuaCrawl vua = new VuaCrawl();
		vua.crawl();
		

	}
}
