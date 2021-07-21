import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {


	private static int millor = Integer.MAX_VALUE;
	private static int total = 0;
	private static int millorX = 0;

	  private static void printAll(int[] config, int k, boolean isSolution) {
	      System.out.print(k + ": [");
	      for (int i = 0; i <= k; i++) {
	          System.out.print(config[i] + ",");
	      }
	      System.out.print("]");
	      if (isSolution) {
	          System.out.println(" (SOL)");
	      } else {
	          System.out.println();
	      }
	  }

	  public static Boolean isValid(Food[] f, int[] config,int k,boolean fact, Double[] max, int[] opt){
			int energy=0;
			Double fat = 0.0;
			boolean fi = false;

      if(fact){

        for(int p=0; p < k+1; p++){
          energy += f[p].getEnergetic_value()*config[p];
          fat += f[p].getFat()*config[p];
        }


        if(energy <= 3000 && energy != 0){
          if(fat <= energy*0.35 && fat != 0){
            fi = true;
          }
        }

      } else {
        for(int i=0; i<f.length ;i++){
  				energy += f[i].getEnergetic_value()*config[i];
  				fat += f[i].getFat()*config[i];
  			}
  			if(energy >= 1000 && energy <= 3000){
  				if(fat >= (float)energy*0.2 && fat <= energy*0.35){
            if(Math.abs((energy/fat) - 5) < Math.abs((max[0]/max[1])-5)){
    					max[0] = (double)energy;
    					max[1] = fat;
							for (int i = 0; i < config.length; i++) {
				         opt[i] = config[i];
				      }
              fi = true;
            }
  				}
  			}


      }

	    return fi;
	  }

		public static int sumaValors(int[] config){
			int total=0;
			for(int i=0; i<config.length ;i++){
				if(config[i]==1){
					total++;
				}
			}
			return total;
		}

	  public static void foodBacktracking(Food[] f, int k, int[] config, Double[] max, int[] opt){
      config[k] = 0;

      while(config[k] < 2){
        if(sumaValors(config) > 4 || k == f.length - 1){
					if(isValid(f,config,k,false,max, opt)){

					}
        }else{
					if(isValid(f,config,k,true,max, opt)){
						foodBacktracking(f,k+1, config, max, opt);
					}
				}
      	config[k]++;
    	}
  	}

		private static int ompleGrups(Equip[][] g, Equip[] e){
			int Esp = 0, dim=0, cont=0;
			for(int i=0; i<e.length ;i++){
				if(e[i].getNationality().equalsIgnoreCase("España")){
					g[Esp][dim] = e[i];
					e[i] = null;
					Esp++;
					cont++;
					if(Esp==g.length){
						Esp = 0;
						dim++;
					}
				}
			}
			for (int i = 0; i < g.length; i++){
				for (int j = 0; j < g.length-i-1; j++){
					for (int k = 0; k < g[0].length; k++){
						for (int m = 0; m < g[0].length-k-1; m++){
							if (g[j][m]!=null && g[j+1][m]!=null && g[j][m].getWinrate() > g[j+1][m].getWinrate()){
								Equip temp = g[j][m];
								g[j][m] = g[j+1][m];
								g[j+1][m] = temp;
							}
						}
					}
				}
			}
			return cont;
		}

		public static String getCounter(Champion c1, Champion[] champ){
			String count="";
			for(int i =0; i<champ.length ;i++){
				if(c1.getName().equalsIgnoreCase(champ[i].getName())){
					count = champ[i].getCounter_picks();
				}
			}
			return count;
		}

		private static int totalCounters(Champion[] champ, Equip e, Equip[][] g, int k){
			int total = 0;
			String counter = "";
			if(g[k][0]!=null & e!=null) {
				for (Jugador j : g[k][0].getPlayers()) {
					counter = getCounter(j.getMain_champion().get(0), champ);
					for (Jugador play : e.getPlayers()) {
						if (play.getMain_champion().get(0).getName().equalsIgnoreCase(counter)) {
							total++;
						}
					}
				}
			} else {
				total = Integer.MAX_VALUE;
			}
			return total;
		}

		public static void grupsBnB(Equip[][] g, Equip[] e, int[] config, Champion[] champ, int k, int Esp, int indise) {

			int j = 0;
			while (config[j] < g.length && k < Esp) {

				int d = totalCounters(champ, e[j], g, k);
				if (d < millor) {
					if (config[j] == 0) {
						millor = d;
						millorX = j;
					}

				}

				if (j == e.length-1) {

					config[millorX] = total + 1;
					total++;
					j = 0;
					millor = Integer.MAX_VALUE;
					g[k][indise] = e[millorX];
					e[millorX] = null;
					grupsBnB(g, e, config, champ, k + 1, Esp , indise);

				} else {
					j++;
				}
			}
		}


		  public static void llistaFitxers(final String pattern, final File folder, List<String> result) {
		      for (final File f : Objects.requireNonNull(folder.listFiles())) {
		          if (f.isDirectory()) {
		              llistaFitxers(pattern, f, result);
		          }
		          if (f.isFile()) {
		              if (f.getName().matches(pattern)) {
		                  result.add(f.getName());
		              }
		          }
		      }
		  }

	    public static void main(String[] args) {
					boolean sortir = false;

					while(!sortir){
						System.out.println("Quina opció vols consultar?");
						System.out.println("\t1)Menú més equilibrat");
						System.out.println("\t2)Fase de Grups");
						System.out.println("\t3)Sortir");

						System.out.print("\nTria: ");
						Scanner in = new Scanner(System.in);
						int opcio = in.nextInt();

						String arxiu = "";

						if(opcio == 1){
								Path current2 = Paths.get("src/main/resources/datasets_food");
								String datasets = current2.toAbsolutePath().toString();

								System.out.println("\n\nQuin dataset vols consultar? ");
								final File folder = new File(datasets);

								List<String> result = new ArrayList<>();

								llistaFitxers(".*\\.json", folder, result);
								int num = 1;
								for (String s : result) {
										System.out.print(num + ":" + s + "   ");
										num++;
								}
								System.out.print("\n\nTria: ");

								Scanner f = new Scanner(System.in);
								int fitxer = f.nextInt();

								Path current = Paths.get("src/main/resources/datasets_food/" + result.get(fitxer-1));
								arxiu = current.toAbsolutePath().toString();


						} else if(opcio == 2){
								Path current2 = Paths.get("src/main/resources/datasets_champ");
								String datasets2 = current2.toAbsolutePath().toString();

								System.out.println("\n\nQuin dataset vols consultar? ");
								final File folder = new File(datasets2);

								List<String> result = new ArrayList<>();

								llistaFitxers(".*\\.json", folder, result);
								int num = 1;
								for (String s : result) {
										System.out.print(num + ":" + s + "   ");
										num++;
								}
								System.out.print("\n\nTria: ");

								Scanner c = new Scanner(System.in);
								int fitxer = c.nextInt();

								Path current = Paths.get("src/main/resources/datasets_champ/" + result.get(fitxer-1));
								arxiu = current.toAbsolutePath().toString();

						}else if(opcio == 3){
							sortir = true;
						}
						long startTime1 = 0;
						long endTime1   = 0;
						long startTime2 = 0;
						long endTime2   = 0;

						if(opcio==1 || opcio == 2){
							try(BufferedReader reader = new BufferedReader(new FileReader(arxiu))){
									switch(opcio){
										case 1:
											Gson gson = new GsonBuilder().create();
											Food[] foods = gson.fromJson(reader, Food[].class);
											int[] config = new int[foods.length];
											int[] opt = new int[foods.length];
											Double[] max = new Double[2];
											config[0] = -1;
											max[0] = 1.0;
											max[1] = 1.0;
											startTime1 = System.nanoTime();
											startTime2 = System.currentTimeMillis();
											foodBacktracking(foods, 0, config, max, opt);
											endTime1 = System.nanoTime();
											endTime2 = System.currentTimeMillis();
											int cont=0;
											Double kcal = 0.0;
											Double tgras = 0.0;
											long totalTime = endTime1 - startTime1;
											long totalTimeMS = endTime2 - startTime2;
											long totalTimeS = totalTimeMS/1000;
											System.out.println("\n\nEl algorisme ha trigat " + totalTime + "ns  " + totalTimeMS + "ms  " + totalTimeS + "s " + " en executar-se.\n");
											System.out.println("\n\nLa combinació de plats més òptima és: ");
											for(int i=0; i<foods.length; i++){
												if(opt[i]==1){
													cont++;
													System.out.println("\tPlato " + cont + ": " + foods[i].getName() + " contiene " + foods[i].getEnergetic_value() + "kcal y " + foods[i].getFat() + "g de grasas.");
													kcal += foods[i].getEnergetic_value();
													tgras += foods[i].getFat();
												}
											}
											System.out.println("Aquest menu te un total de: " + kcal + "kcal y " + tgras + "g de grasses. ");
											System.out.println("Aquestes grasses suposen el: "+ (tgras/kcal)*100 + "% del total de calories.\n");
											break;
										case 2:
											Gson gson2 = new GsonBuilder().create();
											Equip[] equips = gson2.fromJson(reader, Equip[].class);

											Path champs = Paths.get("src/main/resources/dataset_Champ.json");
											String file = champs.toAbsolutePath().toString();

											try(BufferedReader buffer = new BufferedReader(new FileReader(file))){
												Gson gsonChamps = new GsonBuilder().create();
												Champion[] campeones = gsonChamps.fromJson(buffer, Champion[].class);


												if(equips.length%6==0){
													Equip[][] grups = new Equip[((int)equips.length/6)][6];

													int t = ompleGrups(grups, equips);

													int[] configu = new int[equips.length];
													Arrays.fill(configu,0);
													System.out.println("\n\nPer " + equips.length + " equips s'han fet " + grups.length + " grups.");
													System.out.println("Hi ha un total de " + t + " espanyols.");
													System.out.println("La distribució per grups és la següent:\n ");
													int init = 0;
													int inici=1;
													if(t>grups.length){
														init = t%grups.length;
														inici = t/grups.length;
														t=grups.length;
														total = init;
													}
													startTime1 = System.nanoTime();
													startTime2 = System.currentTimeMillis();
													for(int f=inici; f<6 ;f++){
														grupsBnB(grups,equips,configu,campeones,init, t,  f);
														total = 0;
														init = 0;
														Arrays.fill(configu,0);
													}
													endTime1 = System.nanoTime();
													endTime2 = System.currentTimeMillis();
													System.out.print("\t");
													long totalTime2 = endTime1 - startTime1;
													long totalTimeMS2 = endTime2 - startTime2;
													long totalTimeS2 = totalTimeMS2/1000;
													for(int marti =0; marti<grups[0].length ;marti++){
														for(int l =0; l<grups.length ;l++){
															if(marti==0){
																System.out.print(grups[l][marti].toString() + "\t");
																if(l<grups.length-1){
																	for(int h=0; h<30-grups[l][marti].toString().length() ;h++){
																		System.out.print(" ");
																	}
																}
															}else if(grups[l][marti]!=null){
																System.out.print("\t" + grups[l][marti].toString() + " - " + totalCounters(campeones, grups[l][marti] , grups , l));
																for(int h=0; h<29-(grups[l][marti].toString().length() + 4)  ;h++){
																	System.out.print(" ");
																}
															}
														}
														System.out.println("");
													}
													System.out.println("\n\nEl algorisme ha trigat " + totalTime2 + "ns  " + totalTimeMS2 + "ms  " + totalTimeS2 + "s " + " en executar-se.\n");
													System.out.println("\nEl format es el següent: <nom_equip>[<nacionalitat>] - <winrate> - <total_counters>");
													System.out.println("\n");


												}else{
													System.out.println("El total de equips no permet una distrubució equitativa en grups de 6. Hi haurá desigualtat en el total de equips per grup.");
												}


											}catch (IOException e){
												e.printStackTrace();
											}



											break;
										case 3:
											break;
									}
							} catch (IOException e) {
									e.printStackTrace();
							}
						}

					}

	    }
	}
