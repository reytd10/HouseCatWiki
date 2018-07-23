BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `catInfo` (
	`id`	integer PRIMARY KEY AUTOINCREMENT,
	`catName`	TEXT,
	`catDetails`	TEXT
);
INSERT INTO `catInfo` VALUES (1,'Abyssinian ','The Abyssinian cat as it is known today was bred in Great Britain. The name ''Abyssinian'' refers to Ethiopia, in reference to widely-spread stories of British soldiers deployed to North Africa in the nineteenth century returning home with kittens purchased from local traders.');
INSERT INTO `catInfo` VALUES (2,'Aegean','The Aegean cat (Greek: γάτα του Αιγαίου gáta tou Aigaíou) is a naturally occurring landrace of domestic cat originating from the Cycladic Islands of Greece. It is considered a natural cat, developing without human interference.[1] Development of the Aegean cat as a formal breed began in the early 1990s by breeders in the fledgling Greek cat fancy, but the variety has yet to be recognized by any major fancier and breeder organization. It is considered to be the only native Greek variety of cat.');
INSERT INTO `catInfo` VALUES (3,'American Curl','The American Curl is a breed of cat characterized by its unusual ears, which curl back from the face toward the center of the back of the skull. An American Curl''s ears should be handled carefully because rough handling may damage the cartilage in the ear. The breed originated in Lakewood, California, as the result of a spontaneous mutation.');
INSERT INTO `catInfo` VALUES (4,'American Bobtail','The American Bobtail is an uncommon breed of domestic cat which was developed in the late 1960s.[1] It is most notable for its stubby "bobbed" tail about one-third to one-half the length of a normal cat''s tail. This is the result of a cat body type genetic mutation affecting the tail development, similar to that of a Manx cat.[1] The breed is not related to the Japanese Bobtail despite the similar name and physical type—the breeding programs are entirely unrelated, and the genetic mutation causing the bobbed tail are known to be different because the mutation causing the American Bobtail''s tail is dominant, whereas the Japanese Bobtail tail mutation is recessive.[2]

American Bobtails are a very sturdy breed, with both short- and long-haired coats. Their coat is shaggy rather than dense or fluffy. They can have any color of eyes and coat, with a strong emphasis on the "wild" tabby appearance in show animals');
INSERT INTO `catInfo` VALUES (5,'American Shorthair','The American Shorthair (ASH) is a breed of domestic cat believed to be descended from European cats brought to North America by early settlers to protect valuable cargo from mice and rats.[1] According to the Cat Fancier''s Association, in 2012, it was the seventh most popular pedigreed cat in the United States.');
CREATE TABLE IF NOT EXISTS `android_metadata` (
	`locale`	TEXT
);
INSERT INTO `android_metadata` VALUES ('en_US');
COMMIT;
