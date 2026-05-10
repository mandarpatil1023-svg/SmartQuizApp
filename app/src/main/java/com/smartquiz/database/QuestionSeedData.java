package com.smartquiz.database;

import java.util.ArrayList;
import java.util.List;

public final class QuestionSeedData {

    private QuestionSeedData() {
    }

    public static List<QuestionEntity> createQuestions() {
        List<QuestionEntity> questions = new ArrayList<>();
        addGeneralKnowledge(questions);
        addScience(questions);
        addCet(questions);
        addOtherExams(questions);
        return questions;
    }

    private static void add(List<QuestionEntity> questions, String category, String question,
                            String optionA, String optionB, String optionC, String optionD,
                            String correctAnswer, String explanation) {
        questions.add(new QuestionEntity(
                category,
                question,
                optionA,
                optionB,
                optionC,
                optionD,
                correctAnswer,
                explanation
        ));
    }

    private static void addGeneralKnowledge(List<QuestionEntity> questions) {
        add(questions, "General Knowledge", "What is the capital of India?", "Mumbai", "New Delhi", "Kolkata", "Chennai", "B", "New Delhi is the capital city of India.");
        add(questions, "General Knowledge", "Who is known as the Father of the Nation in India?", "Jawaharlal Nehru", "Sardar Patel", "Mahatma Gandhi", "Subhas Chandra Bose", "C", "Mahatma Gandhi is widely called the Father of the Nation.");
        add(questions, "General Knowledge", "Which planet is known as the Blue Planet?", "Earth", "Mars", "Venus", "Jupiter", "A", "Earth is called the Blue Planet because of its abundant water.");
        add(questions, "General Knowledge", "Which is the largest ocean on Earth?", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean", "D", "The Pacific Ocean is the largest and deepest ocean.");
        add(questions, "General Knowledge", "How many continents are there in the world?", "Five", "Six", "Seven", "Eight", "C", "There are seven continents in the world.");
        add(questions, "General Knowledge", "Who wrote the national anthem of India?", "Bankim Chandra Chatterjee", "Rabindranath Tagore", "Sarojini Naidu", "Subramania Bharati", "B", "Rabindranath Tagore composed Jana Gana Mana.");
        add(questions, "General Knowledge", "Which is the longest river in the world?", "Amazon", "Nile", "Yangtze", "Mississippi", "B", "The Nile is traditionally regarded as the longest river.");
        add(questions, "General Knowledge", "Which country is known as the Land of the Rising Sun?", "China", "Japan", "Thailand", "South Korea", "B", "Japan is called the Land of the Rising Sun.");
        add(questions, "General Knowledge", "What is the national animal of India?", "Tiger", "Lion", "Elephant", "Leopard", "A", "The Bengal tiger is the national animal of India.");
        add(questions, "General Knowledge", "Who invented the telephone?", "Thomas Edison", "Alexander Graham Bell", "Nikola Tesla", "James Watt", "B", "Alexander Graham Bell is credited with inventing the telephone.");
        add(questions, "General Knowledge", "Which is the smallest state in India by area?", "Goa", "Sikkim", "Tripura", "Nagaland", "A", "Goa is the smallest Indian state by area.");
        add(questions, "General Knowledge", "Which gas do plants absorb from the atmosphere?", "Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen", "C", "Plants absorb carbon dioxide during photosynthesis.");
        add(questions, "General Knowledge", "Who was the first President of India?", "Rajendra Prasad", "S. Radhakrishnan", "Zakir Husain", "Jawaharlal Nehru", "A", "Dr. Rajendra Prasad was the first President of India.");
        add(questions, "General Knowledge", "Which festival is known as the festival of lights?", "Holi", "Diwali", "Eid", "Baisakhi", "B", "Diwali is celebrated as the festival of lights.");
        add(questions, "General Knowledge", "Which is the highest mountain peak in the world?", "K2", "Kanchenjunga", "Mount Everest", "Makalu", "C", "Mount Everest is the highest peak above sea level.");
        add(questions, "General Knowledge", "In which year did India gain independence?", "1945", "1946", "1947", "1950", "C", "India became independent on 15 August 1947.");
        add(questions, "General Knowledge", "Which is the national flower of India?", "Rose", "Lotus", "Sunflower", "Lily", "B", "Lotus is the national flower of India.");
        add(questions, "General Knowledge", "Who wrote the Ramayana?", "Valmiki", "Kalidasa", "Tulsidas", "Ved Vyasa", "A", "The Ramayana is traditionally attributed to Valmiki.");
        add(questions, "General Knowledge", "Which is the largest desert in the world?", "Sahara", "Thar", "Gobi", "Kalahari", "A", "The Sahara is the largest hot desert in the world.");
        add(questions, "General Knowledge", "Which is the currency of Japan?", "Won", "Yen", "Dollar", "Ruble", "B", "Japan uses the yen as its currency.");
        add(questions, "General Knowledge", "Who discovered gravity after observing a falling apple?", "Isaac Newton", "Galileo Galilei", "Albert Einstein", "Johannes Kepler", "A", "Isaac Newton is associated with the gravity apple story.");
        add(questions, "General Knowledge", "Which is the official language of Brazil?", "Spanish", "Portuguese", "French", "English", "B", "Portuguese is the official language of Brazil.");
        add(questions, "General Knowledge", "What is the national bird of India?", "Peacock", "Parrot", "Sparrow", "Eagle", "A", "The Indian peacock is the national bird of India.");
        add(questions, "General Knowledge", "Which city is called the Pink City of India?", "Jaipur", "Udaipur", "Jodhpur", "Bikaner", "A", "Jaipur is known as the Pink City.");
        add(questions, "General Knowledge", "Who was the first man to step on the Moon?", "Yuri Gagarin", "Buzz Aldrin", "Neil Armstrong", "Michael Collins", "C", "Neil Armstrong first walked on the Moon in 1969.");
        add(questions, "General Knowledge", "Which instrument is used to measure temperature?", "Barometer", "Thermometer", "Hygrometer", "Ammeter", "B", "A thermometer is used to measure temperature.");
        add(questions, "General Knowledge", "Which state is famous for the backwaters of India?", "Tamil Nadu", "Kerala", "Karnataka", "Odisha", "B", "Kerala is famous for its backwaters.");
        add(questions, "General Knowledge", "Which is the largest planet in the solar system?", "Earth", "Saturn", "Jupiter", "Neptune", "C", "Jupiter is the largest planet in the solar system.");
        add(questions, "General Knowledge", "The Taj Mahal is located in which city?", "Delhi", "Jaipur", "Agra", "Lucknow", "C", "The Taj Mahal is located in Agra.");
        add(questions, "General Knowledge", "Which is the fastest land animal?", "Leopard", "Tiger", "Cheetah", "Horse", "C", "The cheetah is the fastest land animal.");
        add(questions, "General Knowledge", "Which day is celebrated as Republic Day in India?", "15 August", "2 October", "26 January", "14 November", "C", "India celebrates Republic Day on 26 January.");
        add(questions, "General Knowledge", "Who composed the song Vande Mataram?", "Rabindranath Tagore", "Bankim Chandra Chatterjee", "Kazi Nazrul Islam", "Sarojini Naidu", "B", "Vande Mataram was written by Bankim Chandra Chatterjee.");
        add(questions, "General Knowledge", "Which country gifted the Statue of Liberty to the USA?", "France", "Germany", "Italy", "Canada", "A", "France gifted the Statue of Liberty to the United States.");
        add(questions, "General Knowledge", "Which continent is the Sahara Desert located in?", "Asia", "Africa", "Australia", "South America", "B", "The Sahara Desert is in northern Africa.");
        add(questions, "General Knowledge", "Who was the first Prime Minister of India?", "Sardar Patel", "Dr. Rajendra Prasad", "Jawaharlal Nehru", "Lal Bahadur Shastri", "C", "Jawaharlal Nehru was India's first Prime Minister.");
        add(questions, "General Knowledge", "What is the chemical symbol of gold?", "Ag", "Au", "Gd", "Go", "B", "The chemical symbol of gold is Au.");
        add(questions, "General Knowledge", "Which is the largest continent?", "Africa", "Europe", "Asia", "North America", "C", "Asia is the largest continent by area.");
        add(questions, "General Knowledge", "Which river is considered holy and flows through Varanasi?", "Yamuna", "Godavari", "Ganga", "Narmada", "C", "The Ganga flows through Varanasi and is considered holy.");
        add(questions, "General Knowledge", "Which sport is associated with Wimbledon?", "Cricket", "Football", "Tennis", "Hockey", "C", "Wimbledon is a famous tennis tournament.");
        add(questions, "General Knowledge", "Which freedom fighter founded the Azad Hind Fauj?", "Bhagat Singh", "Subhas Chandra Bose", "Bal Gangadhar Tilak", "Lala Lajpat Rai", "B", "Subhas Chandra Bose led the Indian National Army.");
        add(questions, "General Knowledge", "Which organ in the human body pumps blood?", "Liver", "Lungs", "Heart", "Kidney", "C", "The heart pumps blood throughout the body.");
        add(questions, "General Knowledge", "Which city is the financial capital of India?", "Delhi", "Mumbai", "Bengaluru", "Ahmedabad", "B", "Mumbai is considered the financial capital of India.");
        add(questions, "General Knowledge", "Which is the largest democracy in the world?", "USA", "India", "Brazil", "Indonesia", "B", "India is the largest democracy in the world.");
        add(questions, "General Knowledge", "Who painted the Mona Lisa?", "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo", "C", "The Mona Lisa was painted by Leonardo da Vinci.");
        add(questions, "General Knowledge", "What is the national currency of the United Kingdom?", "Dollar", "Euro", "Pound sterling", "Franc", "C", "The United Kingdom uses the pound sterling.");
        add(questions, "General Knowledge", "Which state is known as the Tea Garden of India?", "Assam", "Punjab", "Haryana", "Mizoram", "A", "Assam is famous for its tea gardens.");
        add(questions, "General Knowledge", "Which metal is liquid at room temperature?", "Iron", "Mercury", "Aluminium", "Copper", "B", "Mercury remains liquid at normal room temperature.");
        add(questions, "General Knowledge", "Which Indian city is known as Silicon Valley of India?", "Hyderabad", "Pune", "Bengaluru", "Chennai", "C", "Bengaluru is called the Silicon Valley of India.");
        add(questions, "General Knowledge", "Who was the first woman Prime Minister of India?", "Sonia Gandhi", "Pratibha Patil", "Indira Gandhi", "Sarojini Naidu", "C", "Indira Gandhi was the first woman Prime Minister of India.");
        add(questions, "General Knowledge", "What is the study of earthquakes called?", "Meteorology", "Seismology", "Ecology", "Zoology", "B", "Seismology is the scientific study of earthquakes.");
    }

    private static void addScience(List<QuestionEntity> questions) {
        add(questions, "Science", "Which part of the cell is known as the powerhouse of the cell?", "Nucleus", "Mitochondria", "Ribosome", "Chloroplast", "B", "Mitochondria produce energy for the cell.");
        add(questions, "Science", "What is the chemical formula of water?", "H2O", "CO2", "O2", "NaCl", "A", "Water is made of two hydrogen atoms and one oxygen atom.");
        add(questions, "Science", "Which gas is essential for human respiration?", "Nitrogen", "Carbon dioxide", "Oxygen", "Helium", "C", "Humans need oxygen for respiration.");
        add(questions, "Science", "Which vitamin is produced in the skin in sunlight?", "Vitamin A", "Vitamin B12", "Vitamin C", "Vitamin D", "D", "Sunlight helps the body produce Vitamin D.");
        add(questions, "Science", "What is the SI unit of force?", "Joule", "Watt", "Newton", "Pascal", "C", "Force is measured in newtons.");
        add(questions, "Science", "Which organ filters blood in the human body?", "Heart", "Kidney", "Lungs", "Pancreas", "B", "Kidneys filter blood and remove wastes.");
        add(questions, "Science", "Plants prepare their food by which process?", "Respiration", "Transpiration", "Photosynthesis", "Digestion", "C", "Plants make food by photosynthesis.");
        add(questions, "Science", "Which planet has the most prominent ring system?", "Earth", "Mars", "Saturn", "Mercury", "C", "Saturn is known for its prominent rings.");
        add(questions, "Science", "Which blood group is called the universal donor?", "AB+", "O-", "A+", "B-", "B", "O negative blood can be donated to most people.");
        add(questions, "Science", "What is the normal boiling point of water at sea level?", "90 degree C", "95 degree C", "100 degree C", "110 degree C", "C", "At standard pressure water boils at 100 degree Celsius.");
        add(questions, "Science", "Which particle has a negative charge?", "Proton", "Neutron", "Electron", "Photon", "C", "Electrons carry negative charge.");
        add(questions, "Science", "Which human organ is primarily responsible for pumping blood?", "Liver", "Heart", "Brain", "Lungs", "B", "The heart pumps blood through the circulatory system.");
        add(questions, "Science", "The process of conversion of liquid into gas is called?", "Condensation", "Evaporation", "Melting", "Freezing", "B", "Evaporation changes liquid into gas.");
        add(questions, "Science", "Which metal is the best conductor of electricity?", "Iron", "Copper", "Silver", "Aluminium", "C", "Silver is the best conductor among common metals.");
        add(questions, "Science", "What is the speed of light in vacuum approximately?", "3 x 10^8 m/s", "3 x 10^6 m/s", "1.5 x 10^8 m/s", "9.8 m/s", "A", "Light travels at about 3 x 10^8 meters per second in vacuum.");
        add(questions, "Science", "Which acid is present in lemon?", "Acetic acid", "Citric acid", "Sulfuric acid", "Lactic acid", "B", "Lemon contains citric acid.");
        add(questions, "Science", "Which branch of science deals with plants?", "Zoology", "Botany", "Geology", "Astronomy", "B", "Botany is the study of plants.");
        add(questions, "Science", "What is the pH value of pure water?", "5", "6", "7", "8", "C", "Pure water is neutral with pH 7.");
        add(questions, "Science", "Which gas is released during photosynthesis?", "Carbon dioxide", "Oxygen", "Hydrogen", "Nitrogen", "B", "Photosynthesis releases oxygen.");
        add(questions, "Science", "What type of lens is used to correct myopia?", "Convex lens", "Concave lens", "Cylindrical lens", "Bifocal lens", "B", "A concave lens corrects near-sightedness.");
        add(questions, "Science", "Which part of the brain controls balance of the body?", "Cerebrum", "Medulla", "Cerebellum", "Hypothalamus", "C", "The cerebellum coordinates balance and posture.");
        add(questions, "Science", "What is the unit of electrical resistance?", "Volt", "Ampere", "Ohm", "Tesla", "C", "Electrical resistance is measured in ohms.");
        add(questions, "Science", "Which planet is closest to the Sun?", "Mercury", "Venus", "Earth", "Mars", "A", "Mercury is the nearest planet to the Sun.");
        add(questions, "Science", "Which gas is used in electric bulbs?", "Oxygen", "Argon", "Nitrogen", "Hydrogen", "B", "Argon is commonly used in incandescent bulbs.");
        add(questions, "Science", "Which is the hardest natural substance?", "Iron", "Diamond", "Quartz", "Graphite", "B", "Diamond is the hardest natural substance.");
        add(questions, "Science", "Which scientist proposed the three laws of motion?", "Galileo", "Albert Einstein", "Isaac Newton", "Kepler", "C", "Newton formulated the three laws of motion.");
        add(questions, "Science", "What is the function of red blood cells?", "Fight infection", "Carry oxygen", "Produce hormones", "Digest food", "B", "Red blood cells transport oxygen.");
        add(questions, "Science", "Which instrument is used to look at stars and planets?", "Microscope", "Periscope", "Telescope", "Barometer", "C", "A telescope is used to observe distant celestial objects.");
        add(questions, "Science", "Which non-metal is liquid at room temperature?", "Bromine", "Chlorine", "Sulfur", "Phosphorus", "A", "Bromine is a liquid non-metal at room temperature.");
        add(questions, "Science", "What is the main gas found in the air we breathe?", "Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen", "C", "Nitrogen makes up most of Earth's atmosphere.");
        add(questions, "Science", "Which organ in plants conducts photosynthesis mainly?", "Root", "Stem", "Leaf", "Flower", "C", "Leaves are the main site of photosynthesis.");
        add(questions, "Science", "The unit of power is?", "Newton", "Joule", "Watt", "Volt", "C", "Power is measured in watts.");
        add(questions, "Science", "Which vitamin helps in blood clotting?", "Vitamin A", "Vitamin C", "Vitamin K", "Vitamin E", "C", "Vitamin K is important for blood clotting.");
        add(questions, "Science", "Which gas causes the greenhouse effect the most among these?", "Oxygen", "Carbon dioxide", "Helium", "Neon", "B", "Carbon dioxide is a major greenhouse gas.");
        add(questions, "Science", "What is the smallest unit of matter?", "Cell", "Molecule", "Atom", "Compound", "C", "Atoms are the basic units of matter.");
        add(questions, "Science", "Which organ is affected by hepatitis?", "Liver", "Kidney", "Lungs", "Stomach", "A", "Hepatitis mainly affects the liver.");
        add(questions, "Science", "Which force pulls objects toward Earth?", "Magnetic force", "Friction", "Gravity", "Electrostatic force", "C", "Gravity pulls objects toward Earth.");
        add(questions, "Science", "What is the center of an atom called?", "Electron shell", "Nucleus", "Proton cloud", "Neutron ring", "B", "The nucleus is the central part of an atom.");
        add(questions, "Science", "Which metal is used in thermometers traditionally?", "Mercury", "Iron", "Copper", "Lead", "A", "Mercury was traditionally used in thermometers.");
        add(questions, "Science", "Which scientist discovered penicillin?", "Louis Pasteur", "Alexander Fleming", "Edward Jenner", "Gregor Mendel", "B", "Alexander Fleming discovered penicillin.");
        add(questions, "Science", "Which process takes place in the kidneys?", "Digestion", "Filtration", "Respiration", "Circulation", "B", "Kidneys filter blood and form urine.");
        add(questions, "Science", "What is the freezing point of water in Celsius?", "0", "10", "32", "100", "A", "Water freezes at 0 degree Celsius.");
        add(questions, "Science", "Which of these is a renewable source of energy?", "Coal", "Petrol", "Solar energy", "Diesel", "C", "Solar energy is renewable.");
        add(questions, "Science", "The study of heredity is called?", "Ecology", "Genetics", "Cytology", "Anatomy", "B", "Genetics studies heredity and variation.");
        add(questions, "Science", "Which blood cells help fight infection?", "Red blood cells", "Platelets", "White blood cells", "Plasma", "C", "White blood cells help defend the body.");
        add(questions, "Science", "Which simple machine is used to draw water from a well?", "Lever", "Pulley", "Wedge", "Screw", "B", "A pulley helps lift the bucket from a well.");
        add(questions, "Science", "Which scientist developed the theory of relativity?", "Isaac Newton", "Albert Einstein", "Niels Bohr", "J. J. Thomson", "B", "Albert Einstein proposed the theory of relativity.");
        add(questions, "Science", "Which part of the eye controls the amount of light entering?", "Retina", "Cornea", "Iris", "Lens", "C", "The iris controls the size of the pupil.");
        add(questions, "Science", "Which disease is caused by deficiency of insulin?", "Asthma", "Diabetes", "Anemia", "Malaria", "B", "Diabetes is related to insufficient insulin or insulin response.");
        add(questions, "Science", "What type of energy is stored in food?", "Mechanical", "Chemical", "Electrical", "Nuclear", "B", "Food stores chemical energy.");
    }

    private static void addCet(List<QuestionEntity> questions) {
        add(questions, "CET Aptitude", "What is 15 + 27?", "40", "42", "44", "46", "B", "15 + 27 equals 42.");
        add(questions, "CET Aptitude", "What is 18 x 3?", "48", "52", "54", "56", "C", "18 multiplied by 3 equals 54.");
        add(questions, "CET Aptitude", "If 72 divided by 8 equals?", "7", "8", "9", "10", "C", "72 divided by 8 is 9.");
        add(questions, "CET Aptitude", "Find the next number: 2, 4, 8, 16, ?", "20", "24", "30", "32", "D", "Each term is doubled, so the next is 32.");
        add(questions, "CET Aptitude", "What is the square of 12?", "124", "142", "144", "154", "C", "12 x 12 equals 144.");
        add(questions, "CET Aptitude", "If a pen costs 12 rupees, what will 5 pens cost?", "50", "55", "60", "65", "C", "5 x 12 equals 60.");
        add(questions, "CET Aptitude", "What is 25 percent of 200?", "25", "40", "50", "75", "C", "25 percent of 200 is 50.");
        add(questions, "CET Aptitude", "Choose the odd one out: 3, 5, 7, 10", "3", "5", "7", "10", "D", "10 is even while the others are odd primes.");
        add(questions, "CET Aptitude", "If 9 workers finish a task in 6 days, total worker-days are?", "15", "36", "45", "54", "D", "9 multiplied by 6 gives 54 worker-days.");
        add(questions, "CET Aptitude", "What is the average of 10, 20 and 30?", "15", "20", "25", "30", "B", "The average is (10 + 20 + 30) / 3 = 20.");
        add(questions, "CET Aptitude", "A train travels 120 km in 2 hours. Its speed is?", "50 km/h", "55 km/h", "60 km/h", "65 km/h", "C", "Speed equals distance divided by time = 60 km/h.");
        add(questions, "CET Aptitude", "What is the simple interest on 1000 rupees at 10 percent for 1 year?", "50", "75", "100", "110", "C", "Simple interest is principal x rate x time = 100.");
        add(questions, "CET Aptitude", "If all cats are animals and some animals are wild, which statement is definitely true?", "All animals are cats", "Some cats are wild", "All cats are animals", "All wild things are cats", "C", "The first statement directly says all cats are animals.");
        add(questions, "CET Aptitude", "Find the missing number: 5, 10, 20, 40, ?", "60", "70", "80", "90", "C", "Each number is doubled, so the next is 80.");
        add(questions, "CET Aptitude", "What is 7 x 8 - 10?", "36", "46", "56", "66", "B", "7 x 8 = 56 and 56 - 10 = 46.");
        add(questions, "CET Aptitude", "If 3 notebooks cost 45 rupees, one notebook costs?", "10", "12", "15", "18", "C", "45 divided by 3 equals 15.");
        add(questions, "CET Aptitude", "The ratio 2:3 is equal to?", "4:5", "6:9", "8:9", "10:12", "B", "2:3 simplifies to the same value as 6:9.");
        add(questions, "CET Aptitude", "What is 30 percent of 150?", "35", "40", "45", "50", "C", "30 percent of 150 equals 45.");
        add(questions, "CET Aptitude", "Which number is a prime number?", "21", "27", "29", "33", "C", "29 has only two factors, 1 and 29.");
        add(questions, "CET Aptitude", "What is the value of 2^5?", "16", "24", "32", "64", "C", "2 raised to the power 5 equals 32.");
        add(questions, "CET Aptitude", "A shop gives 10 percent discount on 500 rupees. Discount amount is?", "25", "40", "50", "60", "C", "10 percent of 500 is 50.");
        add(questions, "CET Aptitude", "Choose the correct mirror pair: ABC : ?", "CBA", "BAC", "CAB", "ACB", "A", "The reverse order of ABC is CBA.");
        add(questions, "CET Aptitude", "What comes next in the series: 1, 4, 9, 16, ?", "20", "24", "25", "36", "C", "These are perfect squares, so the next is 25.");
        add(questions, "CET Aptitude", "If a rectangle has length 8 and breadth 5, area is?", "13", "26", "40", "45", "C", "Area = length x breadth = 40.");
        add(questions, "CET Aptitude", "Find the missing letter: A, C, E, G, ?", "H", "I", "J", "K", "B", "The sequence skips one letter each time, so next is I.");
        add(questions, "CET Aptitude", "What is 144 divided by 12?", "11", "12", "13", "14", "B", "144 divided by 12 equals 12.");
        add(questions, "CET Aptitude", "If 5x = 35, x equals?", "5", "6", "7", "8", "C", "35 divided by 5 gives 7.");
        add(questions, "CET Aptitude", "The perimeter of a square with side 6 is?", "12", "18", "24", "36", "C", "Perimeter of a square is 4 x side = 24.");
        add(questions, "CET Aptitude", "A man walks 3 km north and 4 km east. Shortest distance from start is?", "5 km", "6 km", "7 km", "8 km", "A", "Using Pythagoras theorem, distance is 5 km.");
        add(questions, "CET Aptitude", "If January has 31 days, how many days do 3 Januaries have?", "62", "90", "93", "96", "C", "31 x 3 = 93.");
        add(questions, "CET Aptitude", "Find the odd one out: Circle, Square, Triangle, Cuboid", "Circle", "Square", "Triangle", "Cuboid", "D", "Cuboid is a 3D figure while the others are 2D.");
        add(questions, "CET Aptitude", "What is 11 x 11?", "111", "121", "131", "141", "B", "11 multiplied by 11 equals 121.");
        add(questions, "CET Aptitude", "The fraction 3/4 is equal to?", "0.25", "0.5", "0.75", "1.25", "C", "3 divided by 4 equals 0.75.");
        add(questions, "CET Aptitude", "If a clock shows 3:00, what is the angle between the hour and minute hands?", "45 degree", "90 degree", "120 degree", "180 degree", "B", "At 3:00 the hands are at right angles.");
        add(questions, "CET Aptitude", "Choose the related pair: Book : Read :: Food : ?", "Cook", "Eat", "Serve", "Buy", "B", "Food is eaten just as a book is read.");
        add(questions, "CET Aptitude", "What is the HCF of 12 and 18?", "2", "3", "6", "9", "C", "The highest common factor of 12 and 18 is 6.");
        add(questions, "CET Aptitude", "What is the LCM of 4 and 6?", "10", "12", "14", "24", "B", "The least common multiple of 4 and 6 is 12.");
        add(questions, "CET Aptitude", "If a number is divisible by both 2 and 3, it is divisible by?", "4", "5", "6", "8", "C", "A number divisible by both 2 and 3 is divisible by 6.");
        add(questions, "CET Aptitude", "Find the missing number: 100, 90, 80, 70, ?", "50", "55", "60", "65", "C", "The pattern decreases by 10, so next is 60.");
        add(questions, "CET Aptitude", "What is 13 + 14 + 15?", "40", "41", "42", "43", "C", "13 + 14 + 15 = 42.");
        add(questions, "CET Aptitude", "A bag contains 5 red and 3 blue balls. Total balls are?", "5", "8", "10", "15", "B", "5 + 3 = 8 balls.");
        add(questions, "CET Aptitude", "If the cost price is 80 and selling price is 100, profit is?", "10", "15", "20", "25", "C", "Profit equals selling price minus cost price = 20.");
        add(questions, "CET Aptitude", "Find the next alphabet pair: AZ, BY, CX, ?", "DW", "EV", "FU", "DV", "A", "The first letter moves forward and the second moves backward, so DW.");
        add(questions, "CET Aptitude", "How many sides does a hexagon have?", "5", "6", "7", "8", "B", "A hexagon has 6 sides.");
        add(questions, "CET Aptitude", "What is 81 divided by 9?", "7", "8", "9", "10", "C", "81 divided by 9 equals 9.");
        add(questions, "CET Aptitude", "If one side of a cube is doubled, its volume becomes?", "2 times", "4 times", "6 times", "8 times", "D", "Volume is proportional to cube of side, so 2^3 = 8.");
        add(questions, "CET Aptitude", "Choose the word that does not belong: Apple, Mango, Carrot, Banana", "Apple", "Mango", "Carrot", "Banana", "C", "Carrot is a vegetable while the others are fruits.");
        add(questions, "CET Aptitude", "A father is 40 and son is 10. After how many years will the father be twice the son's age?", "15", "18", "20", "25", "C", "After 20 years they will be 60 and 30, so father is twice the son.");
        add(questions, "CET Aptitude", "What is the complement of 35 degree?", "45 degree", "55 degree", "65 degree", "75 degree", "B", "Complementary angles add up to 90 degree, so 90 - 35 = 55.");
        add(questions, "CET Aptitude", "If SOUTH is written as HTUOS, then TRAIN is written as?", "NIART", "TNIAR", "ARTIN", "RNIAT", "A", "The pattern is simply reversing the letters.");
    }

    private static void addOtherExams(List<QuestionEntity> questions) {
        add(questions, "Other Exams", "Who was the first Governor-General of independent India?", "C. Rajagopalachari", "Lord Mountbatten", "Dr. Rajendra Prasad", "Jawaharlal Nehru", "A", "C. Rajagopalachari was the first Indian Governor-General of independent India.");
        add(questions, "Other Exams", "Which article of the Indian Constitution deals with equality before law?", "Article 14", "Article 19", "Article 21", "Article 32", "A", "Article 14 guarantees equality before law.");
        add(questions, "Other Exams", "Which is the largest public sector bank in India?", "Canara Bank", "Bank of Baroda", "Punjab National Bank", "State Bank of India", "D", "State Bank of India is the largest public sector bank.");
        add(questions, "Other Exams", "MS Word is an example of which type of software?", "System software", "Application software", "Firmware", "Utility software", "B", "MS Word is application software.");
        add(questions, "Other Exams", "What is the full form of CPU?", "Central Process Unit", "Central Processing Unit", "Computer Processing Unit", "Control Processing Unit", "B", "CPU stands for Central Processing Unit.");
        add(questions, "Other Exams", "Which institution issues currency notes in India?", "Finance Ministry", "Reserve Bank of India", "State Bank of India", "SEBI", "B", "The Reserve Bank of India issues most currency notes.");
        add(questions, "Other Exams", "Who appoints the Prime Minister of India?", "Chief Justice", "President", "Vice President", "Lok Sabha Speaker", "B", "The President appoints the Prime Minister.");
        add(questions, "Other Exams", "Which day is observed as National Voters Day in India?", "25 January", "26 January", "15 August", "2 October", "A", "National Voters Day is observed on 25 January.");
        add(questions, "Other Exams", "The headquarters of the United Nations is in?", "Geneva", "Paris", "New York", "London", "C", "The UN headquarters is in New York.");
        add(questions, "Other Exams", "What does HTTP stand for?", "Hyper Text Transfer Protocol", "High Text Transfer Program", "Hyperlink Transfer Process", "Home Tool Transfer Protocol", "A", "HTTP stands for Hyper Text Transfer Protocol.");
        add(questions, "Other Exams", "Which Indian state has the largest population?", "Maharashtra", "Bihar", "Uttar Pradesh", "West Bengal", "C", "Uttar Pradesh has the largest population among Indian states.");
        add(questions, "Other Exams", "Who is the custodian of the Indian Constitution?", "Prime Minister", "Supreme Court", "President", "Parliament Secretary", "B", "The Supreme Court protects and interprets the Constitution.");
        add(questions, "Other Exams", "Which tax replaced many indirect taxes in India in 2017?", "VAT", "GST", "Service Tax", "Excise", "B", "GST was introduced in 2017 as a major indirect tax reform.");
        add(questions, "Other Exams", "Which is the shortcut key for copy in most computers?", "Ctrl + X", "Ctrl + V", "Ctrl + C", "Ctrl + Z", "C", "Ctrl + C is commonly used for copy.");
        add(questions, "Other Exams", "Who wrote the Constitution of India as chairman of the drafting committee?", "B. R. Ambedkar", "Rajendra Prasad", "Sardar Patel", "Jawaharlal Nehru", "A", "Dr. B. R. Ambedkar chaired the drafting committee.");
        add(questions, "Other Exams", "Which is the largest planet after Jupiter?", "Earth", "Saturn", "Uranus", "Neptune", "B", "Saturn is the second largest planet.");
        add(questions, "Other Exams", "In banking, KYC stands for?", "Know Your Customer", "Keep Your Cash", "Know Your Credit", "Keep Your Customer", "A", "KYC means Know Your Customer.");
        add(questions, "Other Exams", "The term WWW stands for?", "World Wide Web", "World Web Window", "Wide World Web", "World Window Web", "A", "WWW stands for World Wide Web.");
        add(questions, "Other Exams", "Which schedule of the Indian Constitution lists the states and union territories?", "First Schedule", "Second Schedule", "Fifth Schedule", "Seventh Schedule", "A", "The First Schedule lists states and union territories.");
        add(questions, "Other Exams", "Which Mughal emperor built the Red Fort in Delhi?", "Akbar", "Babur", "Shah Jahan", "Aurangzeb", "C", "Shah Jahan built the Red Fort.");
        add(questions, "Other Exams", "What is the full form of ATM in banking?", "Any Time Money", "Automated Teller Machine", "Auto Transfer Machine", "Any Teller Machine", "B", "ATM stands for Automated Teller Machine.");
        add(questions, "Other Exams", "Which Indian city hosts the Reserve Bank of India's central office?", "Delhi", "Mumbai", "Kolkata", "Chennai", "B", "The RBI central office is in Mumbai.");
        add(questions, "Other Exams", "Who was the first woman President of India?", "Indira Gandhi", "Pratibha Patil", "Sarojini Naidu", "Sushma Swaraj", "B", "Pratibha Patil was the first woman President of India.");
        add(questions, "Other Exams", "Which key is used to refresh a webpage on most computers?", "F2", "F5", "F8", "F12", "B", "F5 is commonly used to refresh a webpage.");
        add(questions, "Other Exams", "How many members can be nominated to the Rajya Sabha by the President?", "10", "12", "14", "16", "B", "The President can nominate 12 members to the Rajya Sabha.");
        add(questions, "Other Exams", "Which city is the headquarters of SEBI?", "Mumbai", "Delhi", "Pune", "Ahmedabad", "A", "SEBI headquarters is in Mumbai.");
        add(questions, "Other Exams", "In computer terms, RAM stands for?", "Random Access Memory", "Read Access Memory", "Rapid Access Module", "Remote Access Memory", "A", "RAM stands for Random Access Memory.");
        add(questions, "Other Exams", "Who founded the Maurya Empire?", "Ashoka", "Chandragupta Maurya", "Bindusara", "Harsha", "B", "Chandragupta Maurya founded the Maurya Empire.");
        add(questions, "Other Exams", "Which constitutional body conducts elections in India?", "Finance Commission", "Election Commission", "UPSC", "NITI Aayog", "B", "The Election Commission conducts elections.");
        add(questions, "Other Exams", "Which bank account generally earns interest and allows frequent withdrawals?", "Current account", "Savings account", "Fixed deposit", "Loan account", "B", "Savings accounts typically earn interest and allow withdrawals.");
        add(questions, "Other Exams", "Which device is used to print documents on paper?", "Monitor", "Scanner", "Printer", "Speaker", "C", "A printer produces hard copies on paper.");
        add(questions, "Other Exams", "Which battle established British supremacy in Bengal?", "Battle of Panipat", "Battle of Plassey", "Battle of Buxar", "Battle of Haldighati", "B", "The Battle of Plassey in 1757 was a major British victory.");
        add(questions, "Other Exams", "Which fundamental right protects freedom of speech and expression?", "Right to Equality", "Right to Freedom", "Right against Exploitation", "Cultural Rights", "B", "Freedom of speech is part of the Right to Freedom.");
        add(questions, "Other Exams", "NEFT is mainly used for?", "Cash withdrawal", "Electronic fund transfer", "Cheque printing", "Insurance claim", "B", "NEFT is an electronic fund transfer system.");
        add(questions, "Other Exams", "Which is the brain of the computer?", "Monitor", "Keyboard", "CPU", "Mouse", "C", "The CPU performs core processing tasks.");
        add(questions, "Other Exams", "Who gave the slogan Jai Jawan Jai Kisan?", "Indira Gandhi", "Mahatma Gandhi", "Lal Bahadur Shastri", "B. R. Ambedkar", "C", "Lal Bahadur Shastri gave the slogan Jai Jawan Jai Kisan.");
        add(questions, "Other Exams", "Which constitutional amendment lowered the voting age from 21 to 18?", "42nd Amendment", "44th Amendment", "61st Amendment", "73rd Amendment", "C", "The 61st Amendment reduced the voting age to 18.");
        add(questions, "Other Exams", "A cheque crossed with two parallel lines generally means?", "It is cancelled", "It must be deposited into a bank account", "It can only be used abroad", "It is post-dated", "B", "A crossed cheque is generally deposited into an account.");
        add(questions, "Other Exams", "Which file extension is commonly used for Microsoft Excel files?", ".docx", ".xlsx", ".pptx", ".txt", "B", "Excel files commonly use the .xlsx extension.");
        add(questions, "Other Exams", "Who was the first Indian to win a Nobel Prize?", "C. V. Raman", "Mother Teresa", "Rabindranath Tagore", "Amartya Sen", "C", "Rabindranath Tagore was the first Indian Nobel laureate.");
        add(questions, "Other Exams", "Who presides over the joint sitting of Parliament?", "President", "Prime Minister", "Speaker of Lok Sabha", "Vice President", "C", "The Speaker of the Lok Sabha presides over a joint sitting.");
        add(questions, "Other Exams", "RTGS in banking is mainly used for?", "Small delayed payments", "High-value real-time transfers", "ATM cash loading", "Loan application", "B", "RTGS is used for high-value real-time settlement.");
        add(questions, "Other Exams", "Which of these is an input device?", "Printer", "Monitor", "Keyboard", "Projector", "C", "A keyboard is an input device.");
        add(questions, "Other Exams", "The Quit India Movement was launched in which year?", "1930", "1942", "1947", "1950", "B", "The Quit India Movement began in 1942.");
        add(questions, "Other Exams", "Who can declare a national emergency in India?", "Prime Minister", "President", "Chief Justice", "Parliament Secretary", "B", "The President declares a national emergency under constitutional provisions.");
        add(questions, "Other Exams", "What does IFSC stand for in banking?", "Indian Finance System Code", "Inter Bank Financial Service Code", "Indian Financial System Code", "Internal Fund Settlement Code", "C", "IFSC stands for Indian Financial System Code.");
        add(questions, "Other Exams", "Which of these is an operating system?", "Windows", "MS Excel", "Google Chrome", "PowerPoint", "A", "Windows is an operating system.");
        add(questions, "Other Exams", "Who was known as the Iron Man of India?", "Subhas Chandra Bose", "Sardar Vallabhbhai Patel", "Bhagat Singh", "Lal Bahadur Shastri", "B", "Sardar Vallabhbhai Patel was called the Iron Man of India.");
        add(questions, "Other Exams", "Who is the ex-officio Chairman of the Rajya Sabha?", "President", "Vice President", "Prime Minister", "Speaker", "B", "The Vice President is the ex-officio Chairman of the Rajya Sabha.");
        add(questions, "Other Exams", "What is inflation?", "Decrease in prices", "General rise in prices", "Increase in exports", "Decrease in production", "B", "Inflation means a general rise in prices over time.");
    }
}
